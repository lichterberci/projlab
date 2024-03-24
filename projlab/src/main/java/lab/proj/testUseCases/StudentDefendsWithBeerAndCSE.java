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
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, b, "PickUp", List.of(s));
        boolean success = b.PickUp(s);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, b, "PickUp", Optional.of(success));
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, b, "Activate", new ArrayList<>());
        b.Activate();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, b, "Activate", Optional.empty());

        CSE cse = new CSE();
        Logger.createObject(IndentedDebugPrinter.MAIN, cse, "cse");
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, cse, "PickUp", List.of(s));
        success = cse.PickUp(s);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, cse, "PickUp", Optional.of(success));
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, cse, "Activate", new ArrayList<>());
        cse.Activate();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, cse, "Activate", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, s, "UseDoor", List.of(d));
        success = s.UseDoor(d);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, s, "UseDoor", Optional.of(success));

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, s, "TimePassed", List.of());
        s.TimePassed();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, s, "TimePassed", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t1, "DropOutAll", List.of());
        t1.DropOutAll();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t1, "DropOutAll", Optional.empty());
    }
}
