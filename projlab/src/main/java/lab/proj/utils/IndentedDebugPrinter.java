package lab.proj.utils;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class IndentedDebugPrinter implements DebugPrinter {

	private int indentation;
	private PrintStream outputStream;
	private Map<Object, String> objectNameMap;
	private Deque<Object> objectStack;
	private static IndentedDebugPrinter instance;

	private IndentedDebugPrinter(PrintStream outputStream) {
		this.outputStream = outputStream;
		this.objectStack = new ArrayDeque<>();
		this.indentation = 0;
		this.objectNameMap = new HashMap<>();
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

	@Override
	public <T, U> void createObject(T creator, U createdObject, String nameOfCreatedObject) {
		if (!objectStack.isEmpty() && creator != objectStack.getLast()) throw new AssertionError();
		printIndentations();
		outputStream.printf("create %s%n", nameOfCreatedObject);
		objectNameMap.put(createdObject, nameOfCreatedObject);
	}

	@Override
	public void destroyObject(Object destroyer, Object destroyedObject) {
		if (!objectStack.isEmpty() && destroyer != objectStack.getLast()) throw new AssertionError();
		printIndentations();
		outputStream.printf("destroyed %s%n", objectNameMap.get(destroyedObject));
		objectNameMap.remove(destroyedObject);
	}

	@Override
	public <T, U> void invokeObjectMethod(T caller, U callee, String methodName, List<?> params) {
		if (!objectStack.isEmpty() && caller != objectStack.getLast()) throw new AssertionError();
		printIndentations();
		objectStack.push(callee);
		indentation++;
		outputStream.printf("--> %s.%s(%s)%n", objectNameMap.get(callee), methodName, params.stream().map(Object::toString).collect(Collectors.joining(", ")));
	}

	@Override
	public <T, U, V> void returnFromMethod(T caller, U callee, String methodName, Optional<V> returnValue) {
		if (!objectStack.isEmpty() && callee != objectStack.getLast()) throw new AssertionError();
		indentation--;
		printIndentations();
		objectStack.pop();
		outputStream.printf("<-- %s%n", returnValue.map(Object::toString).orElse(""));
	}

	@Override
	public <T, V> void selfInvokeMethod(T object, String methodName, List<?> args, Optional<V> returnValue) {
		if (!objectStack.isEmpty() && object != objectStack.getLast()) throw new AssertionError();
		printIndentations();
		outputStream.printf("--> %s.%s(%s)%n", objectNameMap.get(object), methodName, args.stream().map(Object::toString).collect(Collectors.joining(", ")));
		outputStream.printf("<-- %s%n", returnValue.map(Object::toString).orElse(""));
	}

	private void printIndentations() {
		for (int i = 0; i < indentation; i++) {
			outputStream.print('\t');
		}
	}
}
