package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.utils.IndentedDebugPrinter;

public class StudentTriesToPickUpABeer implements TestUseCase {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        Student s = new Student();
        Logger.createObject(s, "s");

        BeerMug b = new BeerMug();
        Logger.createObject(b, "b");

        Room location = new Room();
        Logger.createObject(location, "location");
        s.SetLocation(location);
        location.AddItem(b);
        boolean success = b.PickUp(s);
    }
}
