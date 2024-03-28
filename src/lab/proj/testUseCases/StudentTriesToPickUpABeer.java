package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

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
        Logger.invokeMethod(s, "SetLocation", List.of());
        s.SetLocation(location);
        Logger.returnVoid();
        Logger.invokeMethod(location, "AddItem", List.of(b));
        location.AddItem(b);
        Logger.returnVoid();

        Logger.invokeMethod(b, "PickUp", List.of(s));
        boolean success = b.PickUp(s);
        Logger.returnValue(success);
    }
}
