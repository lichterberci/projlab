package lab.proj.testUseCases;

import lab.proj.model.Door;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.model.Teacher;
import lab.proj.utils.IndentedDebugPrinter;

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

        Logger.createObject(r1, "r1");
        Logger.createObject(r2, "r2");
        Logger.createObject(d, "d");

        d.SetRooms(r1, r2);
        Logger.createObject(s, "s");

        s.SetLocation(r1);
        Logger.createObject(t1, "t1");

        t1.SetLocation(r2);
    }
}
