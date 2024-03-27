package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentTriesToPickUpABeer implements TestUseCase {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();
    @Override
    public void runUseCase() {
        Student s = new Student();
        Logger.createObject(IndentedDebugPrinter.MAIN, s, "s");

        BeerMug b = new BeerMug();
        Logger.createObject(IndentedDebugPrinter.MAIN, b, "b");

        Room location = new Room();
        Logger.createObject(IndentedDebugPrinter.MAIN, location, "location");
        Logger.invokeObjectMethod(s, "SetLocation", List.of());
        s.SetLocation(location);
        Logger.returnFromMethod(s, "SetLocation", Optional.empty());
        Logger.invokeObjectMethod(location, "AddItem", List.of(b));
        location.AddItem(b);
        Logger.returnFromMethod(location, "AddItem", Optional.empty());

        Logger.invokeObjectMethod(b, "PickUp", List.of(s));
        boolean success = b.PickUp(s);
        Logger.returnFromMethod(b, "PickUp", Optional.of(success));
    }
}
