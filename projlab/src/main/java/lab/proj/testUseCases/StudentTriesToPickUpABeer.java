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
        Logger.createObject(IndentedDebugPrinter.MAIN, s, "s");

        BeerMug b = new BeerMug();
        Logger.createObject(IndentedDebugPrinter.MAIN, b, "b");

        Room location = new Room();
        Logger.createObject(IndentedDebugPrinter.MAIN, location, "location");
        s.SetLocation(location);
        location.AddItem(b);

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, b, "PickUp", List.of(s));
        boolean success = b.PickUp(s);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, b, "PickUp", Optional.of(success));
    }
}
