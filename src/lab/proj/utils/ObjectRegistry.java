package lab.proj.utils;

import java.util.*;
import java.util.stream.Collectors;

public abstract class ObjectRegistry {

    protected final Map<String, Object> nameToObject = new HashMap<>(); // name -> object mapping

    protected final Map<Object, String> objectToName = new HashMap<>(); // object -> name mapping

    public final String RegisterObject(Object object) {
        String name = GenerateNameToObject(object);
        nameToObject.put(name, object);
        objectToName.put(object, name);
        return name;
    }

    public final void UnregisterObject(Object object) {
        String name = GetName(object);
        nameToObject.remove(name);
        objectToName.remove(object);
    }

    public final Object GetObject(String name) {
        return nameToObject.get(name);
    }


    public final String GetName(Object object) {
        return objectToName.get(object);
    }

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

    protected abstract String GenerateNameToObject(Object object);

    public void Clear() {
        nameToObject.clear();
        objectToName.clear();
    }
}
