package lab.proj.utils;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A debug printer implementation for printing sequence diagrams.
 */
public class SequenceDiagramPrinter implements DebugPrinter {

    public static Object MAIN = new Object();
    private static SequenceDiagramPrinter instance;
    private final PrintStream outputPrinter;
    private final List<Object> lifelines;
    private final Deque<Object> objectStack = new ArrayDeque<>();

    /**
     * Constructs a SequenceDiagramPrinter with a specified output stream.
     * @param outputStream The output stream to which the sequence diagrams will be printed.
     */
    public SequenceDiagramPrinter(PrintStream outputStream) {
        this.outputPrinter = outputStream;
        objectStack.offerLast(MAIN);
        lifelines = new ArrayList<>();
    }

    /**
     * Retrieves the singleton instance of SequenceDiagramPrinter.
     * @return The singleton instance of SequenceDiagramPrinter.
     */
    public static SequenceDiagramPrinter getInstance() {
        if (instance == null)
            instance = new SequenceDiagramPrinter(System.out);
        return instance;
    }

    /**
     * Resets the singleton instance of IndentedDebugPrinter with a target stream.
     * @param targetStream The target stream to which debug messages will be printed.
     * @return The reset singleton instance of IndentedDebugPrinter.
     */
    public static SequenceDiagramPrinter resetInstance(PrintStream targetStream) {
        instance = new SequenceDiagramPrinter(targetStream);
        return instance;
    }

    /**
     * Prints an empty line of lifelines in the sequence diagram.
     */
    public void printEmptyLineOfLifelines() {
        lifelines.forEach(obj -> outputPrinter.print(obj == null ? "       " : "   │   "));
        outputPrinter.println();
    }

    @Override
    public <T, U> void createObject(T creator, U createdObject, String nameOfCreatedObject) {

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
        outputPrinter.print(nameOfCreatedObject);
        outputPrinter.println(" <<creates>>");

        lifelines.add(createdObject);

        printEmptyLineOfLifelines();
    }

    @Override
    public void destroyObject(Object destroyer, Object destroyedObject) {
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
    }

    @Override
    public <T> void invokeObjectMethod(T callee, String methodName, List<?> params) {
        Object caller = objectStack.peekLast();
        printArrowBetweenObjects(caller, callee);

        outputPrinter.print("  ");

        outputPrinter.print(methodName);
        outputPrinter.print('(');
        outputPrinter.print(params.stream().map(Object::toString).collect(Collectors.joining(", ")));
        outputPrinter.println(')');

        printEmptyLineOfLifelines();
    }

    @Override
    public <T, U> void returnFromMethod(T callee, String methodName, Optional<U> returnValue) {
        Object caller = objectStack.pollLast();
        printArrowBetweenObjects(callee, caller);

        outputPrinter.print(' ');

        outputPrinter.print(returnValue.map(Object::toString).orElse("void"));

        outputPrinter.print(" (");
        outputPrinter.print(methodName);
        outputPrinter.println(")");

        printEmptyLineOfLifelines();
    }

    private <T, U> void printArrowBetweenObjects(T from, U to) {
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
                        return indexOfCaller > indexOfCallee ? "   │<──" : "──>│  ";
                    }

                    if (i == indexOfCaller) {
                        return indexOfCaller > indexOfCallee ? "───┤   " : "   ├───";
                    }

                    return lifelines.get(i) != null ? "───┼───" : "───────";
                })
                .forEach(outputPrinter::print);
    }

    @Override
    public <T> void selfInvokeMethod(T object, String methodName, List<?> params) {

        lifelines.stream()
                .map(obj -> {
                    if (obj == null) return "       ";
                    if (obj == object) return "   ├──┬";
                    return "   │   ";
                })
                .forEach(outputPrinter::print);

        outputPrinter.println();

        lifelines.stream()
                .map(obj -> {
                    if (obj == null) return "       ";
                    if (obj == object) return "   │<─┴";
                    return "   │   ";
                })
                .forEach(outputPrinter::print);

        outputPrinter.print(' ');

        outputPrinter.print(methodName);
        outputPrinter.print('(');
        outputPrinter.print(params.stream().map(Object::toString).collect(Collectors.joining(", ")));
        outputPrinter.println(')');

        printEmptyLineOfLifelines();
    }
}
