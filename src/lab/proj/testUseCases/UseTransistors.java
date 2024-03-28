package lab.proj.testUseCases;

import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.utils.IndentedDebugPrinter;

public class UseTransistors extends TransistorPairing {

    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();
    private Student st;
    private Room r1;
    private Room r2;

    @Override
    public void runUseCase() {
        st = new Student();
        r1 = new Room();
        r2 = new Room();
        super.runUseCase();
        Logger.createObject(t1, "t1");
        Logger.createObject(t2, "t2");
        Logger.createObject(r1, "r1");
        Logger.createObject(r2, "r2");
        Logger.createObject(st, "st");

        st.SetLocation(r1);

        r1.AddItem(t1);

        r1.AddItem(t2);

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
