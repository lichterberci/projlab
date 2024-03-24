package lab.proj.testUseCases;

import lab.proj.model.Actor;
import lab.proj.model.Door;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class StudentSwitchesRooms implements TestUseCase {
    @Override
    public void runUseCase() {
        Room r1 = new Room();
        Room r2 = new Room();
        Door d = new Door();
        d.SetRooms(r1, r2);
        Actor a = new Student();
        a.SetLocation(r1);
        IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, r1, "r1");
        IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, r2, "r2");
        IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, d, "d");
        IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, a, "a");
        IndentedDebugPrinter.getInstance().invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, a, "UseDoor", List.of(d));
        boolean result = a.UseDoor(d);
        IndentedDebugPrinter.getInstance().returnFromMethod(IndentedDebugPrinter.CONTROLLER, a, "UseDoor", Optional.of(result));
    }
}
