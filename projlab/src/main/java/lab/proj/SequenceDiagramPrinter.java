package lab.proj;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class SequenceDiagramPrinter {
	private final PrintStream outputStream;
	private List<Object> lifelines;
	public SequenceDiagramPrinter(PrintStream outputStream) {
		this.outputStream = outputStream;
		lifelines = new ArrayList<>();
	}

	public void printEmptyLineOfLifelines() {
		lifelines.forEach(obj -> outputStream.print(obj == null ? "       " : "   |   "));
		outputStream.println();
	}

	public <T, U> void createObject(T creator, U createdObject, String nameOfCreatedObject) {

		final int indexOfCreator = lifelines.indexOf(creator);

		IntStream.range(0, lifelines.size())
			.mapToObj(i -> switch (Integer.signum(Integer.compare(i, indexOfCreator))) {
				case -1 -> "   |   ";
				case 0 -> "   |---";
				case 1 -> "---|---";
				default -> throw new IllegalStateException();
			})
			.forEach(outputStream::print);

		outputStream.print("-> ");
		outputStream.print(nameOfCreatedObject);
		outputStream.println(" <<creates>>");

		lifelines.add(createdObject);

		printEmptyLineOfLifelines();
	}

	public void destroyObject(Object destroyer, Object destroyedObject) {
		final int indexOfDestroyed = lifelines.indexOf(destroyedObject);
		final int indexOfDestroyer = lifelines.indexOf(destroyer);

		final int minIndex = Math.min(indexOfDestroyer, indexOfDestroyed);
		final int maxIndex = Math.max(indexOfDestroyer, indexOfDestroyed);

		IntStream.range(0, lifelines.size())
			.mapToObj(i -> {
				if (i < minIndex || i > maxIndex)
					return "   |   ";
				if (i == indexOfDestroyed) {
					if (indexOfDestroyer > indexOfDestroyed)
						return "   |<--";
					else
						return  "-->|  ";
				}
				if (i == indexOfDestroyer) {
					if (indexOfDestroyer > indexOfDestroyed)
						return "---|   ";
					else
						return  "   |---";
				}
				return "---|---";
			})
			.forEach(outputStream::print);

		outputStream.println(" <<destroys>>");

		lifelines.stream()
				.map(obj -> (obj == null) ? "       "
						: ((obj == destroyedObject)
						? "   X   "
						: "   |   "))
				.forEach(outputStream::print);

		lifelines.set(indexOfDestroyed, null);

		outputStream.println();

		printEmptyLineOfLifelines();
	}

	public <T> void invokeObjectMethod(T callee, Method method, List<?> params) {



	}
}
