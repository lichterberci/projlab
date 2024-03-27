package lab.proj.utils;

import java.util.List;
import java.util.Optional;

/**
 * An interface representing a debug printer for logging object creations, method invocations, and method returns.
 */
public interface DebugPrinter {

    /**
     * Logs the creation of an object.
     *
     * @param creator The creator of the object.
     * @param createdObject The object created.
     * @param nameOfCreatedObject The name of the created object.
     * @param <T> The type of the creator.
     * @param <U> The type of the created object.
     */
    <T, U> void createObject(T creator, U createdObject, String nameOfCreatedObject);

    /**
     * Logs the destruction of an object.
     *
     * @param destroyer The destroyer of the object.
     * @param destroyedObject The object destroyed.
     */
    void destroyObject(Object destroyer, Object destroyedObject);

    /**
     * Logs the invocation of a method on an object.
     *
     * @param <T>        The type of the callee.
     * @param callee     The callee (object) on which the method is invoked.
     * @param methodName The name of the invoked method.
     * @param params     The parameters passed to the method.
     */
    <T> void invokeObjectMethod(T callee, String methodName, List<?> params);

    /**
     * Logs the invocation of a method on the same object.
     *
     * @param object The object on which the method is invoked.
     * @param methodName The name of the invoked method.
     * @param params The parameters passed to the method.
     * @param <T> The type of the object.
     */
    <T> void selfInvokeMethod(T object, String methodName, List<?> params);

    /**
     * Logs the return from a method.
     *
     * @param <T>         The type of the callee.
     * @param <U>         The type of the return value.
     * @param callee      The callee (object) from which the method returns.
     * @param methodName  The name of the method from which the return occurs.
     * @param returnValue The return value (optional) of the method.
     */
    <T, U> void returnFromMethod(T callee, String methodName, Optional<U> returnValue);
}
