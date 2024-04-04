package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.SequenceDiagramPrinter;

public class TwoTeachersOneStudent implements TestUseCase {
    protected static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

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
