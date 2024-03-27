package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class UseTransistors extends TransistorPairing{

    private Student st;
    private Room r1;
    private Room r2;

    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        st = new Student();
        r1 = new Room();
        r2 = new Room();
        super.runUseCase();
        Logger.createObject(IndentedDebugPrinter.MAIN, t1, "t1");
        Logger.createObject(IndentedDebugPrinter.MAIN, t2, "t2");
        Logger.createObject(IndentedDebugPrinter.MAIN, r1, "r1");
        Logger.createObject(IndentedDebugPrinter.MAIN, r2, "r2");
        Logger.createObject(IndentedDebugPrinter.MAIN, st, "st");


        Logger.invokeObjectMethod(st, "SetLocation", List.of(r1));
        st.SetLocation(r1);
        Logger.returnFromMethod(
                st,
                "SetLocation",
                Optional.empty());

        Logger.invokeObjectMethod(r1, "AddItem", List.of(t1));
        r1.AddItem(t1);
        Logger.returnFromMethod(
                r1,
                "AddItem",
                Optional.empty());

        Logger.invokeObjectMethod(r1, "AddItem", List.of(t2));
        r1.AddItem(t2);
        Logger.returnFromMethod(
                r1,
                "AddItem",
                Optional.empty());

        Logger.invokeObjectMethod(t1, "PickUp", List.of(st));
        boolean suc1 = t1.PickUp(st);
        Logger.returnFromMethod(
                t1,
                "PickUp",
                Optional.empty());
        Logger.invokeObjectMethod(t2, "PickUp", List.of(st));
        boolean suc2 = t2.PickUp(st);
        Logger.returnFromMethod(
                t2,
                "PickUp",
                Optional.empty());

        if(suc1 && suc2){
            Logger.invokeObjectMethod(t1, "Activate", List.of());
            t1.Activate();
            Logger.returnFromMethod(
                    t1,
                    "Activate",
                    Optional.empty());
            Logger.invokeObjectMethod(st, "SetLocation", List.of(r2));
            st.SetLocation(r2);
            Logger.returnFromMethod(
                    st,
                    "SetLocation",
                    Optional.empty());

            Logger.invokeObjectMethod(r1, "StepOut", List.of(st));
            r1.StepOut(st);
            Logger.returnFromMethod(
                    r1,
                    "StepOut",
                    Optional.empty());

            Logger.invokeObjectMethod(t2, "Activate", List.of());
            t2.Activate();
            Logger.returnFromMethod(
                    t2,
                    "Activate",
                    Optional.empty());
            Logger.invokeObjectMethod(t2, "Activate", List.of());
            t2.Activate();
            Logger.returnFromMethod(
                    t2,
                    "Activate",
                    Optional.empty());
        }




    }
}
