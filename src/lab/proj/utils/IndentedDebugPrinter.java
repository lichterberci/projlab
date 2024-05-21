package lab.proj.utils;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A debug printer implementation that prints log messages with indentation.
 */
public class IndentedDebugPrinter implements DebugPrinter {

    /**
     * The main object.
     */
    public static final Object MAIN = new Object();
    /**
     * The singleton instance of IndentedDebugPrinter.
     */
    private static IndentedDebugPrinter instance;

    /**
     * The output stream.
     */
    private final PrintStream outputStream;
    /**
     * The map of object names.
     */
    private final Map<Object, String> objectNameMap = new HashMap<>();
    /**
     * The object stack.
     */
    private final Deque<Object> objectStack = new ArrayDeque<>();
    /**
     * The indentation level.
     */
    private int indentation = 0;

    /**
     * Creates a new IndentedDebugPrinter with a target stream.
     *
     * @param outputStream The target stream to which debug messages will be printed.
     */
    private IndentedDebugPrinter(PrintStream outputStream) {
        this.outputStream = outputStream;
        objectNameMap.put(MAIN, "Main");
        objectStack.offerLast(MAIN);
    }

    /**
     * Retrieves the singleton instance of IndentedDebugPrinter.
     *
     * @return The singleton instance of IndentedDebugPrinter.
     */
    public static IndentedDebugPrinter getInstance() {
        if (instance == null)
            instance = new IndentedDebugPrinter(System.out);
        return instance;
    }

    /**
     * Resets the singleton instance of IndentedDebugPrinter with a target stream.
     *
     * @param targetStream The target stream to which debug messages will be printed.
     * @return The reset singleton instance of IndentedDebugPrinter.
     */
    public static IndentedDebugPrinter resetInstance(PrintStream targetStream) {
        instance = new IndentedDebugPrinter(targetStream);
        return instance;
    }

    /**
     * Resets the singleton instance of IndentedDebugPrinter with the default target stream.
     *
     * @return The reset singleton instance of IndentedDebugPrinter.
     */
    @Override
    public String getObjectName(Object object) {
        if (object == null)
            return "null";
        if (object instanceof Collection<?> collection)
            return String.format("[%s]",
                    collection.stream().map(this::getObjectName).collect(Collectors.joining(", ")));
        return objectNameMap.getOrDefault(object, object.toString());
    }

    /**
     * Generates a name for an object.
     *
     * @param createdObject The object created.
     * @return The generated name for the object.
     */
    private String generateNameToObject(Object createdObject) {
        String originalName = createdObject.getClass().getSimpleName();
        StringBuilder newName = new StringBuilder();
        if (originalName.equals("Towel"))
            newName.append("tw");
        else if (originalName.equals("Transistor"))
            newName.append("tr");
        else
            for (int i = 0; i < originalName.length(); i++)
                if (Character.isUpperCase(originalName.charAt(i)))
                    newName.append(Character.toLowerCase(originalName.charAt(i)));
        String nameOfCreatedObject = newName.toString() + 1;
        for (int id = 2; objectNameMap.containsValue(nameOfCreatedObject); id++)
            nameOfCreatedObject = newName.toString() + id;
        return nameOfCreatedObject;
    }

    /**
     * Logs the creation of an object.
     *
     * @param createdObject The object created.
     */
    @Override
    public void createObject(Object createdObject) {
        String nameOfCreatedObject = generateNameToObject(createdObject);
        Object creator = objectStack.peekLast();

        printIndentations();
        outputStream.printf("%s created %s of type %s%n",
                objectNameMap.getOrDefault(creator, "Unknown"),
                nameOfCreatedObject,
                createdObject.getClass().getSimpleName()
        );

        objectNameMap.put(createdObject, nameOfCreatedObject);
    }

    /**
     * Logs the destruction of an object.
     *
     * @param destroyedObject The object destroyed.
     */
    @Override
    public void destroyObject(Object destroyedObject) {
        Object destroyer = objectStack.peekLast();

        printIndentations();
        outputStream.printf("%s destroyed %s%n",
                objectNameMap.getOrDefault(destroyer, "Unknown"),
                objectNameMap.get(destroyedObject));

        objectNameMap.remove(destroyedObject);
    }

    /**
     * Retrieves the name of the caller method.
     *
     * @return The name of the caller method.
     */
    private String getCallerMethodName() {
        List<StackTraceElement> stackTraceElements = Arrays.asList(Thread.currentThread().getStackTrace());
        final String currentLogger = stackTraceElements.get(1).getClassName();
        return stackTraceElements.stream().skip(1)
                .filter(ste -> !currentLogger.equals(ste.getClassName()))
                .findFirst().orElseThrow().getMethodName();
    }

    /**
     * Logs the invocation of a method on an object.
     *
     * @param callee The callee (object) on which the method is invoked.
     * @param params The parameters passed to the method.
     */
    @Override
    public void invokeMethod(Object callee, List<?> params) {
        Object caller = objectStack.peekLast();
        if (caller == callee) {
            selfInvokeMethod(params);
            return;
        }

        objectStack.offerLast(callee);

        printIndentations();
        outputStream.printf("--> %s.%s(%s)%n", objectNameMap.get(callee), getCallerMethodName(),
                params.stream().map(this::getObjectName).collect(Collectors.joining(", ")));

        indentation++;
    }

    /**
     * Logs the invocation of a method on the same object.
     *
     * @param params The parameters passed to the method.
     */
    private void selfInvokeMethod(List<?> params) {
        Object object = objectStack.peekLast();
        objectStack.offerLast(object);

        printIndentations();
        outputStream.printf("--| %s.%s(%s)%n", objectNameMap.get(object), getCallerMethodName(),
                params.stream().map(this::getObjectName).collect(Collectors.joining(", ")));
        printIndentations();
        outputStream.println("<-|");

        indentation++;
    }

    /**
     * Logs the return from a method.
     *
     * @param value The return value (optional) of the method.
     */
    @Override
    public void returnValue(Object value) {
        indentation--;

        printIndentations();
        outputStream.printf("<-- %s%n", getObjectName(value));

        objectStack.pollLast();
    }

    /**
     * Logs the return from a method.
     */
    @Override
    public void returnVoid() {
        indentation--;

        printIndentations();
        outputStream.println("<--");

        objectStack.pollLast();
    }

    /**
     * Prints the indentation.
     */
    private void printIndentations() {
        for (int i = 0; i < indentation; i++) {
            outputStream.print('\t');
        }
    }
}
