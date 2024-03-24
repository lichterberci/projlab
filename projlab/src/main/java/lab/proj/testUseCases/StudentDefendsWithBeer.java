package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.Optional;

public class StudentDefendsWithBeer extends OneTeacherOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        BeerMug b = new BeerMug();
        Logger.createObject(IndentedDebugPrinter.CONTROLLER, b, "b");

        b.PickUp(s);
        s.CollectItem(b);
        s.SetLocation(r2);

        Logger.invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, s, "TimePassed", new ArrayList<>());
        s.TimePassed();
        Logger.returnFromMethod(IndentedDebugPrinter.CONTROLLER, s, "TimePassed", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, t1, "DropOutAll", new ArrayList<>());
        t1.DropOutAll();
        Logger.returnFromMethod(IndentedDebugPrinter.CONTROLLER, t1, "DropOutAll", Optional.empty());
    }
}
