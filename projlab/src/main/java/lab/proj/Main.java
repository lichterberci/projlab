package lab.proj;

import lab.proj.utils.*;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		DebugPrinter printer = IndentedDebugPrinter.resetInstance(System.out);

		Object a = new Object();
		Object b = new Object();
		Object c = new Object();

		printer.createObject(null, a, "a");
		printer.createObject(null, b, "b");
		printer.createObject(null, c, "c");
		printer.selfInvokeMethod(a, "bar", List.of(123, 321), Optional.of(345));
		printer.invokeObjectMethod(a, c, "foo", List.of(123, "asd"));
		printer.destroyObject(c, b);
		printer.returnFromMethod(a, c, "foo", Optional.empty());

	}
}