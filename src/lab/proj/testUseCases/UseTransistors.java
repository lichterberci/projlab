package lab.proj.testUseCases;

import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

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


        Logger.invokeMethod(st, "SetLocation", List.of(r1));
        st.SetLocation(r1);
        Logger.returnValue(
                st,
                "SetLocation",
                Optional.empty());

        Logger.invokeMethod(r1, "AddItem", List.of(t1));
        r1.AddItem(t1);
        Logger.returnValue(
                r1,
                "AddItem",
                Optional.empty());

        Logger.invokeMethod(r1, "AddItem", List.of(t2));
        r1.AddItem(t2);
        Logger.returnValue(
                r1,
                "AddItem",
                Optional.empty());

        Logger.invokeMethod(t1, "PickUp", List.of(st));
        boolean suc1 = t1.PickUp(st);
        Logger.returnValue(
                t1,
                "PickUp",
                Optional.empty());
        Logger.invokeMethod(t2, "PickUp", List.of(st));
        boolean suc2 = t2.PickUp(st);
        Logger.returnValue(
                t2,
                "PickUp",
                Optional.empty());

        if (suc1 && suc2) {
            Logger.invokeMethod(t1, "Activate", List.of());
            t1.Activate();
            Logger.returnValue(
                    t1,
                    "Activate",
                    Optional.empty());
            Logger.invokeMethod(st, "SetLocation", List.of(r2));
            st.SetLocation(r2);
            Logger.returnValue(
                    st,
                    "SetLocation",
                    Optional.empty());

            Logger.invokeMethod(r1, "StepOut", List.of(st));
            r1.StepOut(st);
            Logger.returnValue(
                    r1,
                    "StepOut",
                    Optional.empty());

            Logger.invokeMethod(t2, "Activate", List.of());
            t2.Activate();
            Logger.returnValue(
                    t2,
                    "Activate",
                    Optional.empty());
            Logger.invokeMethod(t2, "Activate", List.of());
            t2.Activate();
            Logger.returnValue(
                    t2,
                    "Activate",
                    Optional.empty());
        }


    }
}
