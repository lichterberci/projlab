package lab.proj.utils;

import jdk.jfr.StackTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class  AutoPrinterProxy <Target> implements InvocationHandler {

	Target me;

	public AutoPrinterProxy(Target me) {
		this.me = me;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		final String methodName = method.getName();

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		// StackTraceElement[0] - is the getStackTrace method
		// StackTraceElement[1] - is the current method

		final Object caller = stackTraceElements[2].getClassName();

		if (me == caller)
			SequenceDiagramPrinter.getInstance().selfInvokeMethod(me, methodName, List.of(args));
		else
			SequenceDiagramPrinter.getInstance().invokeObjectMethod(caller, me, methodName, args == null ? Collections.emptyList() : args.length > 0 ? List.of(args) : Collections.emptyList());

		Object result = method.invoke(me, args);

		if (caller != me) {
			SequenceDiagramPrinter.getInstance().returnFromMethod(caller,
					me,
					methodName,
					method.getGenericReturnType().equals(Void.TYPE) ? Optional.empty() : Optional.of(result));
		}


		return result;
	}
}
