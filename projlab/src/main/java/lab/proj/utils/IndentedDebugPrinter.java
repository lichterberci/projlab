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
	public static Object CONTROLLER = new Object();

	private IndentedDebugPrinter(PrintStream outputStream) {
		this.outputStream = outputStream;
		this.objectStack = new ArrayDeque<>();
		this.indentation = 0;
		this.objectNameMap = new HashMap<>();
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
		printIndentations();
		objectStack.push(callee);
		indentation++;
		outputStream.printf("--> %s.%s(%s)%n", objectNameMap.get(callee), methodName, params.stream().map(val -> objectNameMap.getOrDefault(val, val.toString())).collect(Collectors.joining(", ")));
	}

	@Override
	public <T, U, V> void returnFromMethod(T caller, U callee, String methodName, Optional<V> returnValue) {
		indentation--;
		printIndentations();
		objectStack.pop();
		outputStream.printf("<-- %s%n", returnValue.map(val ->
			objectNameMap.getOrDefault(val, val.toString())
		).orElse(""));
	}

	@Override
	public <T, V> void selfInvokeMethod(T object, String methodName, List<?> args, Optional<V> returnValue) {
		printIndentations();

		outputStream.printf("--> %s.%s(%s)%n", objectNameMap.get(object), methodName, args.stream()
				.map(val -> objectNameMap.getOrDefault(val, val.toString()))
				.collect(Collectors.joining(", ")));

		outputStream.printf(
				"<-- %s%n",
				returnValue
					.map(val -> objectNameMap.getOrDefault(val, val.toString()))
					.orElse("")
		);
	}

	private void printIndentations() {
		for (int i = 0; i < indentation; i++) {
			outputStream.print('\t');
		}
	}
}
