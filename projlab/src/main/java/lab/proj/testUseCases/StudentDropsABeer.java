package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDropsABeer implements TestUseCase{
	@Override
	public void runUseCase() {
		var s = new Student();
		var b = new BeerMug();
		var r1 = new Room();
		s.CollectItem(b);
		IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER,
				s,
				"s");
		IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER,
				b,
				"b");
		IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER,
				r1,
				"r1");
		b.PickUp(s);
		s.SetLocation(r1);
		IndentedDebugPrinter.getInstance().invokeObjectMethod(IndentedDebugPrinter.CONTROLLER,
				b,
				"Drop",
				new ArrayList<>());
		b.Drop();
		IndentedDebugPrinter.getInstance().returnFromMethod(IndentedDebugPrinter.CONTROLLER,
				b,
				"Drop",
				Optional.empty());
	}
}
