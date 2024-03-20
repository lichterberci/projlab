package lab.proj;

import lab.proj.utils.AutoPrintable;
import lab.proj.utils.AutoPrinterProxy;
import lab.proj.utils.SequenceDiagramPrinter;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		SequenceDiagramPrinter printer = new SequenceDiagramPrinter(System.out);

//		Object a = Proxy.newProxyInstance(Object.class.getClassLoader(),
//				new Class[] { AutoPrintable.class },
//				new AutoPrinterProxy());
//		Object b = Proxy.newProxyInstance(Object.class.getClassLoader(),
//				new Class[] { AutoPrintable.class },
//				new AutoPrinterProxy());
//		Object c = Proxy.newProxyInstance(Object.class.getClassLoader(),
//				new Class[] { AutoPrintable.class },
//				new AutoPrinterProxy());

//		Object a = new Object();
//		Object b = new Object();
//		Object c = new Object();
//
//		printer.createObject(null, a, "a");
//		printer.createObject(a, b, "b");
//		printer.createObject(a, c, "c");
//		printer.selfInvokeMethod(a, "bar", List.of(123, 321));
//		printer.invokeObjectMethod(a, c, "foo", List.of(123, "asd"));
//		printer.destroyObject(c, b);
//		printer.returnFromMethod(a, c, "foo", Optional.empty());

		Main m = new Main();
		m.test();
	}

	void test() {
		ITest test = new Test();
		test = (ITest) Proxy.newProxyInstance(ITest.class.getClassLoader(),
				new Class[]{ITest.class},
				new AutoPrinterProxy<>(test));
		test.foo();
	}
}