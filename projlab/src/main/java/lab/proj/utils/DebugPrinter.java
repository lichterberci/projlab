package lab.proj.utils;

import java.util.List;
import java.util.Optional;

public interface DebugPrinter {
    <T, U> void createObject(T creator, U createdObject, String nameOfCreatedObject);

    void destroyObject(Object destroyer, Object destroyedObject);

    <T, U> void invokeObjectMethod(T caller, U callee, String methodName, List<?> params);

    <T, V> void selfInvokeMethod(T object, String methodName, List<?> params, Optional<V> returnValue);

    <T, U, V> void returnFromMethod(T caller, U callee, String methodName, Optional<V> returnValue);
}
