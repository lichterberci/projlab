package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.utils.SequenceDiagramPrinter;

public class StudentDefendsWithBeer extends OneTeacherOneStudent {
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        BeerMug b = new BeerMug();
        boolean success = b.PickUp(s);
        b.Activate();
        success = s.UseDoor(d);
        s.TimePassed();
        t1.DropOutAll();
    }
}
