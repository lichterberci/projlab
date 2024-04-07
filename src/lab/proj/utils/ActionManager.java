package lab.proj.utils;

import lab.proj.model.BeerMug;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ActionManager {

	private final Map<String, Object> objectsInGame;

	public ActionManager() {
		objectsInGame = new HashMap<>();
	}

	public void performAction(String objectName, String actionName, List<String> args) {
		if (actionName.equals("create") && args.size() == 1 && !objectsInGame.containsKey(objectName)) {
			String className = args.get(0);

			try {
				objectsInGame.put(
						objectName,
						BeerMug.class.getClassLoader()
								.loadClass("lab.proj.model.%s".formatted(className))
								.getConstructor()
								.newInstance()
				);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException |
			         NoSuchMethodException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}

			return;
		}

		if (objectsInGame.containsKey(objectName)) {
			final Object object = objectsInGame.get(objectName);

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

								if (objectsInGame.containsKey(arg)) {
									return objectsInGame.get(arg);
								}

								return arg; // string
							}
						}
					})
					.toList();

			try {
				Arrays.stream(object.getClass().getMethods())
						.filter(method -> method.getName().equals(actionName))
						.findFirst()
						.map(method -> {
							method.setAccessible(true);
							return method;
						})
						.orElseThrow(() -> new NoSuchMethodException("Method not found: %s".formatted(actionName)))
						.invoke(object, parsedArgs.toArray());
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				throw new RuntimeException(e);
			}

			return;
		}

		throw new IllegalArgumentException("Object not found: %s".formatted(objectName));
	}
}
