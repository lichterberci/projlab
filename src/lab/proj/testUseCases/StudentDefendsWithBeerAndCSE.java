package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.CSE;
import lab.proj.utils.SequenceDiagramPrinter;

public class StudentDefendsWithBeerAndCSE extends OneTeacherOneStudent {
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        BeerMug b = new BeerMug();

        boolean success = b.PickUp(s);
        b.Activate();

        CSE cse = new CSE();
        cse.SetLifeTime(1);

        success = cse.PickUp(s);
        cse.Activate();
        success = s.UseDoor(d);

        s.TimePassed();
        t1.DropOutAll();
    }
}
