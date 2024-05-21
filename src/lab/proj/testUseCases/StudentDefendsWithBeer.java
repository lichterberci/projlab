package lab.proj.testUseCases;

import lab.proj.model.BeerMug;

/**
 * A class representing a specific use case with one teacher and one student.
 */
public class StudentDefendsWithBeer extends OneTeacherOneStudent {

    /**
     * Runs the use case, invoking the superclass method and activating the BeerMug item.
     */
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
