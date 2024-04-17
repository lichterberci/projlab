package lab.proj.utils;

import lab.proj.model.BeerMug;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class ActionManager {

    private final ObjectRegistry registry = SequenceDiagramPrinter.getInstance().registry;

    public void performAction(String arg0, String arg1, List<String> args) {
        if (arg0.equals("Create")) {
            createObject(arg1);
            return;
        }

        if (arg0.equals("SetSeed")) {
            Randomware.SetSeed(Integer.parseInt(arg1));
            return;
        }

        if (arg0.equals("Status")) {
            printStatusOfObject(arg1);
            return;
        }

        if (registry.GetObject(arg1) != null) {
            callMethodOnObject(arg1, arg0, args);
            return;
        }

        throw new IllegalArgumentException("Object not found: %s".formatted(arg1));
    }

    private void printStatusOfObject(String arg1) {
        final Object object = registry.GetObject(arg1);

        final Set<Field> fields = new HashSet<>(List.of(object.getClass().getDeclaredFields()));

        // add superclass fields
        Class<?> superClass = object.getClass().getSuperclass();

        while (superClass != null) {
            fields.addAll(List.of(superClass.getDeclaredFields()));
            superClass = superClass.getSuperclass();
        }

        fields.stream()
                .sorted((f1, f2) -> String.CASE_INSENSITIVE_ORDER.compare(f1.getName(), f2.getName()))
                .forEach(field -> {
                    try {
                        field.setAccessible(true);

                        Object value = field.get(object);

                        if (field.getAnnotation(ExcludeFromStatus.class) != null
                                || Modifier.isStatic(field.getModifiers())) {
                            return;
                        }

                        String stringRepresentationOfValue = registry.ResolveObject(value);
                        System.out.printf("%s: %s%n", field.getName(), stringRepresentationOfValue);
                    } catch (IllegalAccessException e) {
                        System.err.println(e.getMessage());
                        throw new RuntimeException(e);
                    }
                });
    }

    private void callMethodOnObject(String objectName, String methodName, List<String> args) {
        final Object object = registry.GetObject(objectName);

        final List<Object> parsedArgs = args.stream()
                .map(arg -> {
                    try {
                        return Integer.parseInt(arg);
                    } catch (NumberFormatException e) {
                        try {
                            return Double.parseDouble(arg);
                        } catch (NumberFormatException e2) {
                            if (arg.equals("null")) {
                                return null;
                            }

                            if (arg.equals("true")) {
                                return true;
                            }

                            if (arg.equals("false")) {
                                return false;
                            }

                            return registry.GetObject(arg);
                        }
                    }
                })
                .toList();

        Method methodToCall;

        try {
            methodToCall = Arrays.stream(object.getClass().getMethods())
                    .filter(method -> method.getName().equals(methodName))
                    .findFirst()
                    .map(method -> {
                        method.setAccessible(true);
                        return method;
                    })
                    .orElseThrow(() -> new NoSuchMethodException("Method not found: %s".formatted(methodName)));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        final Map<Class<?>, List<Object>> parameterBinding = Arrays.stream(methodToCall.getParameters())
                .allMatch(param -> param.getType().equals(Set.class)) ?
                parsedArgs.stream().collect(Collectors.groupingBy(Object::getClass))
                : Collections.emptyMap();

        final List<HashSet<Object>> collectedObjectParameters = parameterBinding.isEmpty() ?
                Collections.emptyList()
                : Arrays.stream(methodToCall.getParameters())
                .map(param -> {
                            ParameterizedType parameterizedType = (ParameterizedType) param.getParameterizedType();
                            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

                            if (actualTypeArguments == null || actualTypeArguments.length == 0) {
                                throw new RuntimeException("No actual type arguments found");
                            }

                            Class<?> templateParameter = (Class<?>) actualTypeArguments[0];

                            return new HashSet<>(
                                    parameterBinding.getOrDefault(
                                            templateParameter,
                                            Collections.emptyList()
                                    )
                            );
                        }
                ).toList();
        try {
            methodToCall.invoke(object, collectedObjectParameters.isEmpty() ? parsedArgs.toArray()
                    : collectedObjectParameters.toArray());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void createObject(String className) {
        try {
            // the constructor will create the object in the logger
            Object newObject = BeerMug.class.getClassLoader()
                    .loadClass("lab.proj.model.%s".formatted(className))
                    .getConstructor()
                    .newInstance();

            String nameOfObject = registry.GetName(newObject);

            System.out.printf("Object created: %s%n", nameOfObject);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
