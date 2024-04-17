package lab.proj.testUseCases;

import lab.proj.model.BeerMug;

public class StudentDefendsWithBeer extends OneTeacherOneStudent {


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
