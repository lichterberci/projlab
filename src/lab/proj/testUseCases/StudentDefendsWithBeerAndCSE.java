package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.CSE;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class StudentDefendsWithBeerAndCSE extends OneTeacherOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        BeerMug b = new BeerMug();
        Logger.createObject(b, "b");
        Logger.invokeMethod(b, "PickUp", List.of(s));
        boolean success = b.PickUp(s);
        Logger.returnValue(success);
        Logger.invokeMethod(b, "Activate", List.of());
        b.Activate();
        Logger.returnVoid();

        CSE cse = new CSE(1);
        Logger.createObject(cse, "cse");
        Logger.invokeMethod(cse, "PickUp", List.of(s));
        success = cse.PickUp(s);
        Logger.returnValue(success);
        Logger.invokeMethod(cse, "Activate", List.of());
        cse.Activate();
        Logger.returnVoid();

        Logger.invokeMethod(s, "UseDoor", List.of(d));
        success = s.UseDoor(d);
        Logger.returnValue(success);

        Logger.invokeMethod(s, "TimePassed", List.of());
        s.TimePassed();
        Logger.returnVoid();

        Logger.invokeMethod(t1, "DropOutAll", List.of());
        t1.DropOutAll();
        Logger.returnVoid();
    }
}
