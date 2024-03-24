package lab.proj.utils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SequenceDiagramPrinter implements DebugPrinter {
    private static SequenceDiagramPrinter instance;
    private final PrintStream outputPrinter;
    private final List<Object> lifelines;

    public SequenceDiagramPrinter(PrintStream outputStream) {
        this.outputPrinter = outputStream;
        lifelines = new ArrayList<>();
        instance = this;
    }

    public static SequenceDiagramPrinter getInstance() {
        return instance;
    }

    public void printEmptyLineOfLifelines() {
        lifelines.forEach(obj -> outputPrinter.print(obj == null ? "       " : "   │   "));
        outputPrinter.println();
    }

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

    public <T, U> void invokeObjectMethod(T caller, U callee, String methodName, List<?> params) {

        printArrowBetweenObjects(caller, callee);

        outputPrinter.print("  ");

        outputPrinter.print(methodName);
        outputPrinter.print('(');
        outputPrinter.print(String.join(", ", params.stream().map(Object::toString).collect(Collectors.toList())));
        outputPrinter.println(')');

        printEmptyLineOfLifelines();
    }

    public <T, U, V> void returnFromMethod(T caller, U callee, String methodName, Optional<V> returnValue) {
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

    public <T, V> void selfInvokeMethod(T object, String methodName, List<?> params, Optional<V> returnValue) {

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
