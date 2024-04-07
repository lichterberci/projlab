package lab.proj.utils;

import lab.proj.model.BeerMug;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
		for (int i = 0; i < originalName.length(); i++)
			if (Character.isUpperCase(originalName.charAt(i)))
				newName.append(Character.toLowerCase(originalName.charAt(i)));
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
		final Object object = objects.get(arg1);

		final Set<Field> fields = new HashSet<>(List.of(object.getClass().getDeclaredFields()));

		// add superclass fields
		Class<?> superClass = object.getClass().getSuperclass();

		while (superClass != null) {
			fields.addAll(List.of(superClass.getDeclaredFields()));
			superClass = superClass.getSuperclass();
		}

		fields.forEach(field -> {
					try {
						field.setAccessible(true);

						Object value = field.get(object);

						if (field.getAnnotation(ExcludeFromStatus.class) != null
								|| field.getType().equals(SequenceDiagramPrinter.class)) {
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

													return element.toString();
												})
												.collect(Collectors.joining(", ", "[", "]"));
									}

									return value.toString();
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

		try {
			Arrays.stream(object.getClass().getMethods())
					.filter(method -> method.getName().equals(methodName))
					.findFirst()
					.map(method -> {
						method.setAccessible(true);
						return method;
					})
					.orElseThrow(() -> new NoSuchMethodException("Method not found: %s".formatted(methodName)))
					.invoke(object, parsedArgs.toArray());
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
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

			System.out.println("Object created: %s".formatted(nameOfObject));

			objects.put(nameOfObject, newObject);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException |
		         NoSuchMethodException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
