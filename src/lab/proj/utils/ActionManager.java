package lab.proj.utils;

import lab.proj.controller.GameManager;
import lab.proj.model.BeerMug;

import java.io.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class ActionManager {

    private final ObjectRegistry registry = SequenceDiagramPrinter.getInstance().registry;
    private Scanner scanner;
    private PrintWriter writer;

    public ActionManager(InputStream input, OutputStream output) {
        this.scanner = new Scanner(input);
        this.writer = new PrintWriter(output, true);
    }

    private void performAction(String arg0, String arg1, List<String> args) {
        if (arg0.equals("RunTests")) {
            runAllTestCasesBatched(arg1);
            return;
        }

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

    private void runAllTestCasesBatched(String sourceDirectoryName) {
        File sourceDirectory = new File(sourceDirectoryName);
        String[] filenameList = sourceDirectory.list();
        if (filenameList == null || filenameList.length == 0)
            return;

        Map<File, File> inputOutputMap = Arrays.stream(filenameList)
                .filter(s -> s.startsWith("input"))
                .map(inputName -> Map.entry(
                        new File(sourceDirectoryName, inputName),
                        new File(sourceDirectoryName, inputName.replace("input", "output"))
                ))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        int success = 0, fail = 0, error = 0; // ++;--;??

        int id = 1;

        for (Map.Entry<File, File> testCase : inputOutputMap.entrySet()) {
            try (
                    InputStream testInput = new FileInputStream(testCase.getKey());
                    InputStream testExpected = new FileInputStream(testCase.getValue());
                    ByteArrayOutputStream testOutput = new ByteArrayOutputStream()
            ) {
                SequenceDiagramPrinter.resetInstance(new PrintStream(OutputStream.nullOutputStream()));
                GameManager.GetInstance().ResetGame();
                new ActionManager(testInput, testOutput).runCommandInterpreter();

                String expectedOutput = new String(testExpected.readAllBytes(), StandardCharsets.UTF_8).trim();
                String actualOutput = testOutput.toString(StandardCharsets.UTF_8).replace("\r\n", "\n").trim();

                if (actualOutput.equals(expectedOutput)) {
                    success++;
                    writer.printf("Test %d - OK%n", id);
                } else {
                    fail++;
                    writer.printf("Test %d - FAIL%n", id);
                }
            } catch (IOException e) {
                error++;
                writer.printf("Test %d - ERROR%n", id);
            }

            id++;
        }

        writer.printf("%d / %d / %d (SUCCESS/FAIL/ERROR)%n", success, fail, error);
    }

    public void runCommandInterpreter() {
        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();

            if (inputLine.isBlank())
                continue;

            if (inputLine.equals("exit"))
                break;

            String[] parts = inputLine.split(" ");
            String objectName = parts[0];
            String actionName = parts[1];
            List<String> arguments = List.of(parts).subList(2, parts.length);

            try {
                performAction(objectName, actionName, arguments);
                if (GameManager.GetInstance().isWon())
                    writer.println("Win!");
            } catch (Exception e) {
                writer.println("Hiba történt: " + e.getMessage());
            }
        }
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
                        writer.printf("%s: %s%n", field.getName(), stringRepresentationOfValue);
                    } catch (IllegalAccessException e) {
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

            writer.printf("Object created: %s%n", nameOfObject);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
