package lab.proj.testUseCases;

import lab.proj.model.Room;
import lab.proj.model.Student;

public class UseTransistors extends TransistorPairing {


    private Student st;
    private Room r1;
    private Room r2;

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
