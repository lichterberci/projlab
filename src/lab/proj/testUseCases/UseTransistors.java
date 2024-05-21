package lab.proj.testUseCases;

import lab.proj.model.Room;
import lab.proj.model.Student;

/**
 * A class representing a specific
 * use case with two transistors and one student.
 */
public class UseTransistors extends TransistorPairing {

    /**
     * Runs the use case, invoking the superclass method and switching rooms.
     */
    private Student st;
    /**
     * The first room.
     */
    private Room r1;
    /**
     * The second room.
     */
    private Room r2;

    /**
     * Runs the use case, invoking the superclass method and switching rooms.
     */
    @Override
    public void runUseCase() {
        super.runUseCase();
        st = new Student();
        r1 = new Room();
        r2 = new Room();

        st.SetLocation(r1);

        t1.SetLocation(r1);
        t2.SetLocation(r1);

        boolean suc1 = t1.PickUp(st);
        boolean suc2 = t2.PickUp(st);
        if (suc1 && suc2) {
            t1.Activate();
            st.SetLocation(r2);
            r1.StepOut(st);
            t2.Activate();
            t2.Activate();
        }
    }
}
