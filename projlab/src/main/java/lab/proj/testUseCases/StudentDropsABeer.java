package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.Student;
import lab.proj.utils.DebugPrinter;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class StudentDropsABeer implements TestUseCase{
	@Override
	public void runUseCase() {
		var s = new Student();
		var b = new BeerMug();
		s.CollectItem(b);
		IndentedDebugPrinter.getInstance().createObject(null, s, "s");
		IndentedDebugPrinter.getInstance().createObject(null, b, "b");
		IndentedDebugPrinter.getInstance().invokeObjectMethod(null,
				s,
				"DropItem",
				List.of(b));
		s.DropItem(b);
		IndentedDebugPrinter.getInstance().returnFromMethod(null, s, "DropItem", Optional.empty());
	}
}
