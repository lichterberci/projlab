package lab.proj.utils;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class IndentedDebugPrinter implements DebugPrinter {

    public static Object CONTROLLER = new Object();
    private static IndentedDebugPrinter instance;
    private final PrintStream outputStream;
    private final Map<Object, String> objectNameMap = new HashMap<>();
    private final Deque<Object> objectStack = new ArrayDeque<>();
    private int indentation = 0;

    private IndentedDebugPrinter(PrintStream outputStream) {
        this.outputStream = outputStream;
        objectNameMap.put(CONTROLLER, "Controller");
    }

    public static IndentedDebugPrinter getInstance() {
        if (instance == null)
            instance = new IndentedDebugPrinter(System.out);
        return instance;
    }

    public static IndentedDebugPrinter resetInstance(PrintStream targetStream) {
        instance = new IndentedDebugPrinter(targetStream);
        return instance;
    }

    public String getObjectName(Object object) {
        if (object instanceof Collection<?> collection)
            return String.format("[%s]",
                    collection.stream().map(this::getObjectName).collect(Collectors.joining(", ")));
        return objectNameMap.getOrDefault(object, object.toString());
    }

    @Override
    public <T, U> void createObject(T creator, U createdObject, String nameOfCreatedObject) {
        printIndentations();
        outputStream.printf("%s created %s%n",
                objectNameMap.getOrDefault(creator, "Unknown"),
                nameOfCreatedObject);
        objectNameMap.put(createdObject, nameOfCreatedObject);
    }

    @Override
    public void destroyObject(Object destroyer, Object destroyedObject) {
        printIndentations();
        outputStream.printf("%s destroyed %s%n",
                objectNameMap.getOrDefault(destroyer, "Unknown"),
                objectNameMap.get(destroyedObject));
        objectNameMap.remove(destroyedObject);
    }

    @Override
    public <T, U> void invokeObjectMethod(T caller, U callee, String methodName, List<?> params) {
        if (caller == callee) {
            selfInvokeMethod(caller, methodName, params);
            return;
        }

        objectStack.push(callee);

        printIndentations();
        outputStream.printf("--> %s.%s(%s)%n", objectNameMap.get(callee), methodName,
                params.stream().map(this::getObjectName).collect(Collectors.joining(", ")));

        indentation++;
    }

    @Override
    public <T, V> void selfInvokeMethod(T object, String methodName, List<?> params) {
        objectStack.push(object);

        printIndentations();
        outputStream.printf("--| %s.%s(%s)%n", objectNameMap.get(object), methodName,
                params.stream().map(this::getObjectName).collect(Collectors.joining(", ")));
        printIndentations();
        outputStream.println("<-|");

        indentation++;
    }

    @Override
    public <T, U, V> void returnFromMethod(T caller, U callee, String methodName, Optional<V> returnValue) {
        indentation--;

        printIndentations();
        outputStream.printf("<-- %s%n", returnValue.map(this::getObjectName).orElse(""));

        objectStack.pop();
    }

    private void printIndentations() {
        for (int i = 0; i < indentation; i++) {
            outputStream.print('\t');
        }
    }
}
