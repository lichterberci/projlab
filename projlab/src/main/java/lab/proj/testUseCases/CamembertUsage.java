package lab.proj.testUseCases;

import lab.proj.model.Camembert;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.Optional;

public class CamembertUsage implements TestUseCase {
	@Override
	public void runUseCase() {
		var c = new Camembert();
		var s = new Student();
		var r = new Room();
		r.AddActor(s);
		s.SetLocation(r);
		s.CollectItem(c);
		c.PickUp(s);
		IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, c, "c");
		IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, s, "s");
    	IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, r, "r");
		IndentedDebugPrinter.getInstance().invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, c, "Activate", Collections.emptyList());
		c.Activate();
		IndentedDebugPrinter.getInstance().returnFromMethod(IndentedDebugPrinter.CONTROLLER, c, "Activate", Optional.empty());
	}
}
