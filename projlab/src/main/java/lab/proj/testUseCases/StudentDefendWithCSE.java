package lab.proj.testUseCases;

import lab.proj.model.CSE;
import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDefendWithCSE extends TwoTeachersOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();
    @Override
    public void runUseCase() {
        super.runUseCase();

        CSE cse2;
        boolean choose = AskTheUser.decision("Három életerejű TVSZ-e legyen?");
        if (!choose)
        {
            cse2 = new CSE(1);
            Logger.createObject(IndentedDebugPrinter.MAIN, cse2, "cse2");

            Logger.invokeObjectMethod(cse2, "PickUp", List.of(s));
            boolean csePickedUp = cse2.PickUp(s);
            Logger.returnFromMethod(cse2, "PickUp", Optional.of(csePickedUp));

            Logger.invokeObjectMethod(cse2, "Activate", List.of());
            cse2.Activate();
            Logger.returnFromMethod(cse2, "Activate", Optional.empty());
        } else {
            Logger.invokeObjectMethod(cse, "Activate", List.of());
            cse.Activate();
            Logger.returnFromMethod(cse, "Activate", Optional.empty());
        }

        Logger.invokeObjectMethod(s, "UseDoor", List.of(d));
        boolean success = s.UseDoor(d);
        Logger.returnFromMethod(s, "UseDoor", Optional.of(success));
        Logger.invokeObjectMethod(s, "TimePassed", List.of());
        s.TimePassed();
        Logger.returnFromMethod(s, "TimePassed", Optional.empty());

        Logger.invokeObjectMethod(t1, "DropOutAll", List.of());
        t1.DropOutAll();
        Logger.returnFromMethod(t1, "DropOutAll", Optional.empty());

        Logger.invokeObjectMethod(t2, "DropOutAll", List.of());
        t2.DropOutAll();
        Logger.returnFromMethod(t2, "DropOutAll", Optional.empty());
    }
}