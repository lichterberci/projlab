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
        BeerMug b = new BeerMug();
        Room location = new Room();
        s.SetLocation(location);
        location.AddItem(b);
        boolean success = b.PickUp(s);
        Logger.returnValue(success);
    }
}
