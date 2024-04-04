package lab.proj.testUseCases;

import lab.proj.model.Door;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.model.Teacher;
import lab.proj.utils.SequenceDiagramPrinter;

public class OneTeacherOneStudent implements TestUseCase {
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

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

        d.SetRooms(r1, r2);

        s.SetLocation(r1);

        t1.SetLocation(r2);
    }
}
