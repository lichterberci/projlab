package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.CSE;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDefendsWithBeerAndCSE extends OneTeacherOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();
    @Override
    public void runUseCase() {
        super.runUseCase();

        BeerMug b = new BeerMug();
        Logger.createObject(IndentedDebugPrinter.MAIN, b, "b");
        Logger.invokeObjectMethod(b, "PickUp", List.of(s));
        boolean success = b.PickUp(s);
        Logger.returnFromMethod(b, "PickUp", Optional.of(success));
        Logger.invokeObjectMethod(b, "Activate", List.of());
        b.Activate();
        Logger.returnFromMethod(b, "Activate", Optional.empty());

        CSE cse = new CSE(1);
        Logger.createObject(IndentedDebugPrinter.MAIN, cse, "cse");
        Logger.invokeObjectMethod(cse, "PickUp", List.of(s));
        success = cse.PickUp(s);
        Logger.returnFromMethod(cse, "PickUp", Optional.of(success));
        Logger.invokeObjectMethod(cse, "Activate", List.of());
        cse.Activate();
        Logger.returnFromMethod(cse, "Activate", Optional.empty());

        Logger.invokeObjectMethod(s, "UseDoor", List.of(d));
        success = s.UseDoor(d);
        Logger.returnFromMethod(s, "UseDoor", Optional.of(success));

        Logger.invokeObjectMethod(s, "TimePassed", List.of());
        s.TimePassed();
        Logger.returnFromMethod(s, "TimePassed", Optional.empty());

        Logger.invokeObjectMethod(t1, "DropOutAll", List.of());
        t1.DropOutAll();
        Logger.returnFromMethod(t1, "DropOutAll", Optional.empty());
    }
}
