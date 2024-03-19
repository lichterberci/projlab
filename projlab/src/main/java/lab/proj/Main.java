package lab.proj;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		SequenceDiagramPrinter printer = new SequenceDiagramPrinter(System.out);

		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		printer.createObject(null, a, "a");
		printer.createObject(a, b, "b");
		printer.createObject(a, c, "c");
		printer.destroyObject(c, b);
	}
}