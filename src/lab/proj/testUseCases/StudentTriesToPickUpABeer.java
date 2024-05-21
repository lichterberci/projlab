package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.Room;
import lab.proj.model.Student;

/**
 * A class representing a specific use case with one teacher and one student.
 */
public class StudentTriesToPickUpABeer implements TestUseCase {

    /**
     * Runs the use case, invoking the superclass method and picking up the BeerMug item.
     */
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
