package lab.proj.testUseCases;

import lab.proj.model.Door;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.model.Teacher;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class OneTeacherOneStudent implements TestUseCase {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    protected Student s;
    protected Teacher t1;
    protected Room r1;
    protected Room r2;
    protected Door d;

    @Override
    public void runUseCase() {
        s = new Student();
        t1 = new Teacher();
        r1 = new Room();
        r2 = new Room();
        d = new Door();

        Logger.createObject(IndentedDebugPrinter.MAIN, r1, "r1");
        Logger.createObject(IndentedDebugPrinter.MAIN, r2, "r2");
        Logger.createObject(IndentedDebugPrinter.MAIN, d, "d");

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, d, "SetRooms", List.of(r1, r2));
        d.SetRooms(r1, r2);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, d, "SetRooms", Optional.empty());

        Logger.createObject(IndentedDebugPrinter.MAIN, s, "s");

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, s, "SetLocation", List.of(r1));
        s.SetLocation(r1);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, s, "SetLocation", Optional.empty());

        Logger.createObject(IndentedDebugPrinter.MAIN, t1, "t1");

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t1, "SetLocation", List.of(r2));
        t1.SetLocation(r2);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t1, "SetLocation", Optional.empty());
    }
}
