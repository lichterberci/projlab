package lab.proj.utils;

import lab.proj.model.BeerMug;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
			try {
				// the constructor will create the object in the logger
				Object newObject = BeerMug.class.getClassLoader()
						.loadClass("lab.proj.model.%s".formatted(arg1))
						.getConstructor()
						.newInstance();

				String nameOfObject = generateNameToObject(newObject);

				System.out.println("Object created: %s".formatted(nameOfObject));

				objects.put(nameOfObject, newObject);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException |
			         NoSuchMethodException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}

			return;
		}

		if (arg0.equals("SetSeed")) {
			Randomware.SetSeed(Integer.parseInt(arg1));
			return;
		}

		if (arg0.equals("Status")) {
			System.out.printf("Status of %s: %s%n", arg1, objects.get(arg1).toString());
			return;
		}

		if (objects.containsKey(arg0)) {
			final Object object = objects.get(arg0);

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
						.filter(method -> method.getName().equals(arg1))
						.findFirst()
						.map(method -> {
							method.setAccessible(true);
							return method;
						})
						.orElseThrow(() -> new NoSuchMethodException("Method not found: %s".formatted(arg1)))
						.invoke(object, parsedArgs.toArray());
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				throw new RuntimeException(e);
			}

			return;
		}

		throw new IllegalArgumentException("Object not found: %s".formatted(arg0));
	}
}
