package lab.proj.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ActionManager {

	private final Map<String, Object> objectsInGame;

	public ActionManager() {
		this.objectsInGame = Map.of("controller", gameController);
	}

	public void performAction(String objectName, String actionName, List<String> args) {
		if (actionName.equals("create") && args.size() == 1 && !objectsInGame.containsKey(objectName)) {
			String className = args.get(0);

			try {
				objectsInGame.put(
						objectName,
						getClass()
								.getClassLoader()
								.loadClass(className)
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
			Object object = objectsInGame.get(objectName);

			try {
				Arrays.stream(object.getClass().getDeclaredMethods())
						.filter(method -> method.getName().equals(actionName))
						.findFirst()
						.orElseThrow(() -> new NoSuchMethodException("Method not found: %s".formatted(actionName)))
						.invoke(object, args);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				throw new RuntimeException(e);
			}

			return;
		}

		throw new IllegalArgumentException("Object not found: %s".formatted(objectName));
	}
}
