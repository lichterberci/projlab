package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.CSE;
import lab.proj.testUseCases.TwoTeachersOneStudent;
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

            Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, cse2, "PickUp", List.of(s));
            boolean csePickedUp = cse2.PickUp(s);
            Logger.returnFromMethod(IndentedDebugPrinter.MAIN, cse2, "PickUp", Optional.of(csePickedUp));

            Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, cse2, "Activate", new ArrayList<>());
            cse2.Activate();
            Logger.returnFromMethod(IndentedDebugPrinter.MAIN, cse2, "Activate", Optional.empty());
        } else {
            Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, cse, "Activate", new ArrayList<>());
            cse.Activate();
            Logger.returnFromMethod(IndentedDebugPrinter.MAIN, cse, "Activate", Optional.empty());
        }

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, s, "UseDoor", List.of(d));
        boolean success = s.UseDoor(d);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, s, "UseDoor", Optional.of(success));
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, s, "TimePassed", List.of());
        s.TimePassed();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, s, "TimePassed", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t1, "DropOutAll", List.of());
        t1.DropOutAll();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t1, "DropOutAll", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t2, "DropOutAll", List.of());
        t2.DropOutAll();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t2, "DropOutAll", Optional.empty());
    }
}