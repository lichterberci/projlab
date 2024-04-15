package lab.proj.utils;

import lab.proj.model.BeerMug;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class ActionManager {

    private final Map<String, Object> objects;

    public ActionManager() {
        objects = new HashMap<>();
    }

    private String generateNameToObject(Object createdObject) {
        String originalName = createdObject.getClass().getSimpleName();
        StringBuilder newName = new StringBuilder();
        if (originalName.equals("Towel"))
            newName.append("tw");
        else if (originalName.equals("Transistor"))
            newName.append("tr");
        else {
            for (int i = 0; i < originalName.length(); i++)
                if (Character.isUpperCase(originalName.charAt(i)))
                    newName.append(Character.toLowerCase(originalName.charAt(i)));
        }
        String nameOfCreatedObject = newName.toString() + 1;
        for (int id = 2; objects.containsKey(nameOfCreatedObject); id++)
            nameOfCreatedObject = newName.toString() + id;
        return nameOfCreatedObject;
    }

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

        if (objects.containsKey(arg1)) {
            callMethodOnObject(arg1, arg0, args);
            return;
        }

        throw new IllegalArgumentException("Object not found: %s".formatted(arg0));
    }

    private void printStatusOfObject(String arg1) {
        final Object object = SequenceDiagramPrinter.getInstance().getObject(arg1); // TODO: this is horrible, but works

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

                        String stringRepresentationOfValue = objects.entrySet().stream()
                                .filter(entry -> entry.getValue() == value)
                                .map(Map.Entry::getKey)
                                .findFirst()
                                .orElseGet(() -> {
                                    if (value == null) {
                                        return "null";
                                    }

                                    if (field.getType().equals(List.class)
                                            || field.getType().equals(ArrayList.class)
                                            || field.getType().equals(LinkedList.class)
                                            || field.getType().isArray()
                                    ) {
                                        final List<?> list = field.getType().isArray() ?
                                                Arrays.stream((Object[]) value).toList()
                                                : (List<?>) value;

                                        if (list.isEmpty()) {
                                            return "[]";
                                        }

                                        return list.stream()
                                                .map(element -> {
                                                    if (element == null) {
                                                        return "null";
                                                    }

                                                    if (element.getClass().equals(String.class)) {
                                                        return "\"%s\"".formatted(element);
                                                    }

                                                    if (objects.containsValue(element)) {
                                                        return objects.entrySet().stream()
                                                                .filter(entry -> entry.getValue() == element)
                                                                .map(Map.Entry::getKey)
                                                                .findFirst()
                                                                .orElseThrow();
                                                    }

                                                    return SequenceDiagramPrinter.getInstance().getObjectName(object); // BURN, BURN, BURN
                                                })
                                                .collect(Collectors.joining(", ", "[", "]"));
                                    }

                                    return SequenceDiagramPrinter.getInstance().getObjectName(value);
                                });

                        System.out.printf("%s: %s%n", field.getName(), stringRepresentationOfValue);
                    } catch (IllegalAccessException e) {
                        System.err.println(e.getMessage());
                        throw new RuntimeException(e);
                    }
                });
    }

    private void callMethodOnObject(String objectName, String methodName, List<String> args) {
        final Object object = objects.get(objectName);

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

                            if (objects.containsKey(arg)) {
                                return objects.get(arg);
                            }

                            return arg; // string
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
                                    //System.out.println(((ParameterizedType)param.getType().getClass().getGenericSuperclass())
                                      //      .getActualTypeArguments()[0]);
//                                    final ParameterizedType parameterizedType = (ParameterizedType) param.getParameterizedType().getClass().getGenericSuperclass();
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

            String nameOfObject = generateNameToObject(newObject);

            System.out.printf("Object created: %s%n", nameOfObject);

            objects.put(nameOfObject, newObject);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
