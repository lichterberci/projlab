package lab.proj.utils;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A debug printer implementation for printing sequence diagrams.
 */
public class SequenceDiagramPrinter implements DebugPrinter {

    private static final Object MAIN = new Object();
    private static SequenceDiagramPrinter instance;
    private final PrintStream outputPrinter;
    private final List<Object> lifelines = new ArrayList<>();
    protected final ObjectRegistry registry = new PascalCaseObjectRegistry();
    private final Deque<Object> objectStack = new ArrayDeque<>();

    /**
     * Constructs a SequenceDiagramPrinter with a specified output stream.
     *
     * @param outputStream The output stream to which the sequence diagrams will be printed.
     */
    public SequenceDiagramPrinter(PrintStream outputStream) {
        this.outputPrinter = outputStream;
        objectStack.offerLast(MAIN);
    }

    /**
     * Retrieves the singleton instance of SequenceDiagramPrinter.
     *
     * @return The singleton instance of SequenceDiagramPrinter.
     */
    public static SequenceDiagramPrinter getInstance() {
        if (instance == null)
            instance = new SequenceDiagramPrinter(System.out);
        return instance;
    }

    /**
     * Resets the singleton instance of SequenceDiagramPrinter with a target stream.
     *
     * @param targetStream The target stream to which debug messages will be printed.
     * @return The reset singleton instance of SequenceDiagramPrinter.
     */
    public static SequenceDiagramPrinter resetInstance(PrintStream targetStream) {
        instance = new SequenceDiagramPrinter(targetStream);
        return instance;
    }

    /**
     * Prints an empty line of lifelines in the sequence diagram.
     */
    private void printEmptyLineOfLifelines() {
        lifelines.forEach(obj -> outputPrinter.print(obj == null ? "       " : "   │   "));
        outputPrinter.println();
    }

    @Override
    public String getObjectName(Object object) {
        return registry.ResolveObject(object);
    }

    public Object getObject(String name) {
        return registry.GetObject(name);
    }

    @Override
    public void createObject(Object createdObject) {
        String nameOfCreatedObject = registry.RegisterObject(createdObject);
        Object creator = objectStack.peekLast();

        final int indexOfCreator = lifelines.indexOf(creator);

        IntStream.range(0, lifelines.size())
                .mapToObj(i -> switch (Integer.signum(Integer.compare(i, indexOfCreator))) {
                    case -1 -> "   │   ";
                    case 0 -> "   ├───";
                    case 1 -> "───┼───";
                    default -> throw new IllegalStateException();
                })
                .forEach(outputPrinter::print);

        outputPrinter.print("─> ");
        outputPrinter.printf("%s: %s", nameOfCreatedObject, createdObject.getClass().getSimpleName());
        outputPrinter.println(" <<creates>>");

        lifelines.add(createdObject);

        printEmptyLineOfLifelines();
    }

    @Override
    public void destroyObject(Object destroyedObject) {
        Object destroyer = objectStack.peekLast();
        printArrowBetweenObjects(destroyer, destroyedObject);

        outputPrinter.println(" <<destroys>>");

        lifelines.stream()
                .map(obj -> {
                    if (obj == null) {
                        return "       ";
                    }
                    if (obj == destroyedObject) {
                        return "   X   ";
                    }
                    return "   │   ";
                })
                .forEach(outputPrinter::print);

        lifelines.set(lifelines.indexOf(destroyedObject), null);

        outputPrinter.println();

        printEmptyLineOfLifelines();

        registry.UnregisterObject(destroyedObject);
    }

    private String getCallerMethodName() {
        List<StackTraceElement> stackTraceElements = Arrays.asList(Thread.currentThread().getStackTrace());
        final String currentLogger = stackTraceElements.get(1).getClassName();
        return stackTraceElements.stream().skip(1)
                .filter(ste -> !currentLogger.equals(ste.getClassName()))
                .findFirst().orElseThrow().getMethodName();
    }

    @Override
    public void invokeMethod(Object callee, List<?> params) {
        Object caller = objectStack.peekLast();
        if (caller == callee) {
            selfInvokeMethod(params);
            return;
        }

        objectStack.offerLast(callee);

        printArrowBetweenObjects(caller, callee);

        String methodName = getCallerMethodName();

        outputPrinter.print(' ');

        outputPrinter.print(methodName);
        outputPrinter.print('(');
        outputPrinter.print(params.stream().map(this::getObjectName).collect(Collectors.joining(", ")));
        outputPrinter.println(')');

        printEmptyLineOfLifelines();
    }

    private void selfInvokeMethod(List<?> params) {
        Object object = objectStack.peekLast();
        objectStack.offerLast(object);

        String methodName = getCallerMethodName();

        lifelines.stream()
                .map(obj -> {
                    if (obj == null) return "       ";
                    if (obj == object) return "   ├──┐";
                    return "   │   ";
                })
                .forEach(outputPrinter::print);

        outputPrinter.println();

        lifelines.stream()
                .map(obj -> {
                    if (obj == null) return "       ";
                    if (obj == object) return "   │<─┘";
                    return "   │   ";
                })
                .forEach(outputPrinter::print);

        outputPrinter.print(' ');

        outputPrinter.print(methodName);
        outputPrinter.print('(');
        outputPrinter.print(params.stream().map(this::getObjectName).collect(Collectors.joining(", ")));
        outputPrinter.println(')');

        printEmptyLineOfLifelines();
    }

    @Override
    public void returnValue(Object returnValue) {
        Object callee = objectStack.pollLast();
        Object caller = objectStack.peekLast();
        printArrowBetweenObjects(callee, caller);

        String methodName = getCallerMethodName();

        outputPrinter.print(' ');

        outputPrinter.print(getObjectName(returnValue));

        outputPrinter.print(" (");
        outputPrinter.print(methodName);
        outputPrinter.println(")");

        printEmptyLineOfLifelines();
    }

    @Override
    public void returnVoid() {
        Object callee = objectStack.pollLast();
        Object caller = objectStack.peekLast();
        printArrowBetweenObjects(callee, caller);

        String methodName = getCallerMethodName();

        outputPrinter.print(' ');

        outputPrinter.print("void");

        outputPrinter.print(" (");
        outputPrinter.print(methodName);
        outputPrinter.println(")");

        printEmptyLineOfLifelines();
    }

    private void printArrowBetweenObjects(Object from, Object to) {
        final int indexOfCaller = lifelines.indexOf(from);
        final int indexOfCallee = lifelines.indexOf(to);

        final int minIndex = Math.min(indexOfCaller, indexOfCallee);
        final int maxIndex = Math.max(indexOfCaller, indexOfCallee);

        IntStream.range(0, lifelines.size())
                .mapToObj(i -> {
                    if (i < minIndex || i > maxIndex) {
                        return lifelines.get(i) != null ? "   │   " : "       ";
                    }

                    if (i == indexOfCallee) {
                        return indexOfCaller > indexOfCallee ? "   │<──" : "──>│   ";
                    }

                    if (i == indexOfCaller) {
                        return indexOfCaller > indexOfCallee ? "───┤   " : "   ├───";
                    }

                    return lifelines.get(i) != null ? "───┼───" : "───────";
                })
                .forEach(outputPrinter::print);
    }
}
