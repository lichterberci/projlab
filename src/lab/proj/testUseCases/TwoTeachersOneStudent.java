package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.SequenceDiagramPrinter;

/**
 * A class representing a specific use case with two teachers and one student.
 */
public class TwoTeachersOneStudent implements TestUseCase {
    /**
     * The logger instance for debugging purposes.
     */
    protected static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    /**
     * The student in the use case.
     */
    protected Student s;
    /**
     * The first teacher in the use case.
     */
    protected Teacher t1;
    /**
     * The second teacher in the use case.
     */
    protected Teacher t2;
    /**
     * The first room in the use case.
     */
    protected Room r1;
    /**
     * The second room in the use case.
     */
    protected Room r2;
    /**
     * The door in the use case.
     */
    protected Door d;
    /**
     * The beer mug in the use case.
     */
    protected BeerMug b;
    /**
     * The CSE item in the use case.
     */
    protected CSE cse;
    /**
     * The Camembert item in the use case.
     */
    protected Camembert cm;
    /**
     * The SlideRule item in the use case.
     */
    protected SlideRule sr;

    /**
     * Runs the use case, invoking the superclass method and setting up the rooms and items.
     */
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

        sr.SetLocation(r2);
    }
}
