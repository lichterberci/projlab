package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.CSE;
import lab.proj.utils.IndentedDebugPrinter;

public class StudentDefendsWithBeerAndCSE extends OneTeacherOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        BeerMug b = new BeerMug();
        boolean success = b.PickUp(s);
        Logger.returnValue(success);
        b.Activate();
        CSE cse = new CSE();
        cse.SetLifeTime(1);
        success = cse.PickUp(s);
        Logger.returnValue(success);
        cse.Activate();
        success = s.UseDoor(d);
        Logger.returnValue(success);

        s.TimePassed();
        t1.DropOutAll();
    }
}
