package lab.proj.testUseCases;

import lab.proj.model.Door;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.model.Teacher;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class TwoTeachersOneStudent implements TestUseCase {
	@Override
	public void runUseCase() {
		var s = new Student();
		var t1 = new Teacher();
		var t2 = new Teacher();
		var r1 = new Room();
		var r2 = new Room();
		var d = new Door();

		IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, s, "s");
		IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, t1, "t1");
		IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, t2, "t2");
		IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, r1, "r1");
		IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, r2, "r2");
		IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, d, "d");

		IndentedDebugPrinter.getInstance().invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, s, "SetLocation", List.of(r1));
		s.SetLocation(r1);
		IndentedDebugPrinter.getInstance().returnFromMethod(IndentedDebugPrinter.CONTROLLER, s, "SetLocation", Optional.empty());

		IndentedDebugPrinter.getInstance().invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, t1, "SetLocation", List.of(r1));
    	t1.SetLocation(r1);
    	IndentedDebugPrinter.getInstance().returnFromMethod(IndentedDebugPrinter.CONTROLLER, t1, "SetLocation", Optional.empty());

    	IndentedDebugPrinter.getInstance().invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, t2, "SetLocation", List.of(r2));
    	t2.SetLocation(r2);
    	IndentedDebugPrinter.getInstance().returnFromMethod(IndentedDebugPrinter.CONTROLLER, t2, "SetLocation", Optional.empty());

    	IndentedDebugPrinter.getInstance().invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, d, "SetRooms", List.of(r1, r2));
    	d.SetRooms(r1, r2);
    	IndentedDebugPrinter.getInstance().returnFromMethod(IndentedDebugPrinter.CONTROLLER, d, "SetRooms", Optional.empty());
	}
}
