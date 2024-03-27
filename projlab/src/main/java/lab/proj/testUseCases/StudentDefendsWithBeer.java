package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDefendsWithBeer extends OneTeacherOneStudent {
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
