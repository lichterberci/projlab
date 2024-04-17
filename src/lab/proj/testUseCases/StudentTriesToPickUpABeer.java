package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.Room;
import lab.proj.model.Student;

public class StudentTriesToPickUpABeer implements TestUseCase {


    @Override
    public void runUseCase() {
        Student s = new Student();
        BeerMug b = new BeerMug();
        Room location = new Room();
        s.SetLocation(location);
        b.SetLocation(location);
        boolean success = b.PickUp(s);
    }
}
