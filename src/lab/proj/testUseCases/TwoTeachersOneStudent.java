package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class TwoTeachersOneStudent implements TestUseCase {
    protected static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

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

        Logger.createObject(s, "s");
        Logger.createObject(t1, "t1");
        Logger.createObject(t2, "t2");
        Logger.createObject(r1, "r1");
        Logger.createObject(r2, "r2");
        Logger.createObject(d, "d");
        Logger.createObject(b, "b");
        Logger.createObject(cse, "cse");
        Logger.createObject(cm, "cm");
        Logger.createObject(sr, "sr");

        s.SetLocation(r1);

        t1.SetLocation(r2);

        t2.SetLocation(r2);

        d.SetRooms(r1, r2);

        boolean beerPickedUp = b.PickUp(s);

        boolean csePickedUp = cse.PickUp(s);

        boolean camembertPickedUp = cm.PickUp(s);

        r2.AddItem(sr);
    }
}
