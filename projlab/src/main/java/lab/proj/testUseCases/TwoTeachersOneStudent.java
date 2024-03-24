package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class TwoTeachersOneStudent implements TestUseCase {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    protected Student s;
    protected Teacher t1;
    protected Teacher t2;
    protected Room r1;
    protected Room r2;
    protected Door d;
    protected BeerMug b;
    protected CSE cse;
    protected Camembert cm;
    protected SlideRule sr;

    @Override
    public void runUseCase() {
        s = new Student();
        t1 = new Teacher();
        t2 = new Teacher();
        r1 = new Room();
        r2 = new Room();
        d = new Door();
        b = new BeerMug();
        cse = new CSE();
        cm = new Camembert();
        sr = new SlideRule();

        Logger.createObject(IndentedDebugPrinter.CONTROLLER, s, "s");
        Logger.createObject(IndentedDebugPrinter.CONTROLLER, t1, "t1");
        Logger.createObject(IndentedDebugPrinter.CONTROLLER, t2, "t2");
        Logger.createObject(IndentedDebugPrinter.CONTROLLER, r1, "r1");
        Logger.createObject(IndentedDebugPrinter.CONTROLLER, r2, "r2");
        Logger.createObject(IndentedDebugPrinter.CONTROLLER, d, "d");
        Logger.createObject(IndentedDebugPrinter.CONTROLLER, b, "b");
        Logger.createObject(IndentedDebugPrinter.CONTROLLER, cse, "cse");
        Logger.createObject(IndentedDebugPrinter.CONTROLLER, cm, "cm");
        Logger.createObject(IndentedDebugPrinter.CONTROLLER, sr, "sr");

        Logger.invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, s, "SetLocation", List.of(r1));
        s.SetLocation(r1);
        Logger.returnFromMethod(IndentedDebugPrinter.CONTROLLER, s, "SetLocation", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, t1, "SetLocation", List.of(r2));
        t1.SetLocation(r2);
        Logger.returnFromMethod(IndentedDebugPrinter.CONTROLLER, t1, "SetLocation", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, t2, "SetLocation", List.of(r2));
        t2.SetLocation(r2);
        Logger.returnFromMethod(IndentedDebugPrinter.CONTROLLER, t2, "SetLocation", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, d, "SetRooms", List.of(r1, r2));
        d.SetRooms(r1, r2);
        Logger.returnFromMethod(IndentedDebugPrinter.CONTROLLER, d, "SetRooms", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, b, "PickUp", List.of(s));
        boolean beerPickedUp = b.PickUp(s);
        Logger.returnFromMethod(IndentedDebugPrinter.CONTROLLER, b, "PickUp", Optional.of(beerPickedUp));

        Logger.invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, cse, "PickUp", List.of(s));
        boolean csePickedUp = cse.PickUp(s);
        Logger.returnFromMethod(IndentedDebugPrinter.CONTROLLER, cse, "PickUp", Optional.of(csePickedUp));

        Logger.invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, cm, "PickUp", List.of(s));
        boolean camembertPickedUp = cm.PickUp(s);
        Logger.returnFromMethod(IndentedDebugPrinter.CONTROLLER, cm, "PickUp", Optional.of(camembertPickedUp));

        Logger.invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, r2, "AddItem", List.of(sr));
        r2.AddItem(sr);
        Logger.returnFromMethod(IndentedDebugPrinter.CONTROLLER, r2, "AddItem", Optional.empty());
    }
}
