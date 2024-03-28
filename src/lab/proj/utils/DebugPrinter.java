package lab.proj.utils;

import java.util.List;

/**
 * An interface representing a debug printer for logging object creations, method invocations, and method returns.
 */
public interface DebugPrinter {

    /**
     * Logs the creation of an object.
     *
     * @param createdObject       The object created.
     * @param nameOfCreatedObject The name of the created object.
     */
    void createObject(Object createdObject);

    /**
     * Logs the destruction of an object.
     *
     * @param destroyedObject The object destroyed.
     */
    void destroyObject(Object destroyedObject);

    /**
     * Logs the invocation of a method on an object.
     *
     * @param callee The callee (object) on which the method is invoked.
     * @param params The parameters passed to the method.
     */
    void invokeMethod(Object callee, List<?> params);

    /**
     * Logs the return from a method.
     *
     * @param value The return value (optional) of the method.
     */
    void returnValue(Object value);

    /**
     * Logs the return from a method.
     */
    void returnVoid();
}
