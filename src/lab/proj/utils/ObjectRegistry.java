package lab.proj.utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A utility class for managing objects and their names.
 */
public abstract class ObjectRegistry {

    /**
     * The map of object names.
     */
    protected final Map<String, Object> nameToObject = new HashMap<>(); // name -> object mapping

    /**
     * The map of object names.
     */
    protected final Map<Object, String> objectToName = new HashMap<>(); // object -> name mapping

    /**
     * Registers an object with a name.
     *
     * @param object The object to register.
     * @return The name of the object.
     */
    public final String RegisterObject(Object object) {
        String name = GenerateNameToObject(object);
        nameToObject.put(name, object);
        objectToName.put(object, name);
        return name;
    }

    /**
     * Unregisters an object.
     *
     * @param object The object to unregister.
     */
    public final void UnregisterObject(Object object) {
        String name = GetName(object);
        nameToObject.remove(name);
        objectToName.remove(object);
    }

    /**
     * Retrieves an object by name.
     *
     * @param name The name of the object.
     * @return The object with the given name.
     */
    public final Object GetObject(String name) {
        return nameToObject.get(name);
    }

    /**
     * Retrieves the name of an object.
     *
     * @param object The object for which to retrieve the name.
     * @return The name of the object.
     */
    public final String GetName(Object object) {
        return objectToName.get(object);
    }

    /**
     * Resolves an object to a string representation.
     *
     * @param object The object to resolve.
     * @return The string representation of the object.
     */
    public String ResolveObject(Object object) {
        if (object == null)
            return "null";

        if (objectToName.containsKey(object))
            return objectToName.get(object);

        if (object.getClass().equals(String.class))
            return "\"%s\"".formatted(object);

        if (object instanceof Collection<?> || object.getClass().isArray()) {
            final Collection<?> collection = object.getClass().isArray() ?
                    Arrays.stream((Object[]) object).toList()
                    : (Collection<?>) object;

            if (collection.isEmpty())
                return "[]";

            return collection.stream()
                    .map(this::ResolveObject)
                    .sorted()
                    .collect(Collectors.joining(", ", "[", "]"));
        }

        return object.toString();
    }

    /**
     * Generates a name for an object.
     *
     * @param object The object created.
     * @return The generated name for the object.
     */
    protected abstract String GenerateNameToObject(Object object);

    /**
     * Clears the object registry.
     */
    public void Clear() {
        nameToObject.clear();
        objectToName.clear();
    }
}
