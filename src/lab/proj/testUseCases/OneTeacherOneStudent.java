package lab.proj.testUseCases;

import lab.proj.model.Door;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.model.Teacher;

/**
 * A class representing a specific use case with one teacher and one student.
 */
public class OneTeacherOneStudent implements TestUseCase {

    /**
     * The student, teacher, and rooms in the use case.
     */
    protected Student s;

    /**
     * The student, teacher, and rooms in the use case.
     */
    protected Teacher t1;

    /**
     * The student, teacher, and rooms in the use case.
     */
    protected Room r1;

    /**
     * The student, teacher, and rooms in the use case.
     */
    protected Room r2;

    /**
     * The student, teacher, and rooms in the use case.
     */
    protected Door d;

    /**
     * Runs the use case, creating the student, teacher, and rooms and setting the student in one room and the teacher in another.
     */
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
