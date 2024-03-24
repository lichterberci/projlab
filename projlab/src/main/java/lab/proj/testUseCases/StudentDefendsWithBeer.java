package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class StudentDefendsWithBeer extends OneTeacherOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        BeerMug b = new BeerMug();
        Logger.createObject(IndentedDebugPrinter.MAIN, b, "b");

        b.PickUp(s);
        s.CollectItem(b);
        s.SetLocation(r2);

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, s, "TimePassed", List.of());
        s.TimePassed();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, s, "TimePassed", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t1, "DropOutAll", List.of());
        t1.DropOutAll();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t1, "DropOutAll", Optional.empty());
    }
}
