package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.utils.IndentedDebugPrinter;

public class StudentDefendsWithBeer extends OneTeacherOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        BeerMug b = new BeerMug();
        Logger.createObject(b, "b");
        boolean success = b.PickUp(s);
        b.Activate();
        success = s.UseDoor(d);
        s.TimePassed();
        t1.DropOutAll();
    }
}
