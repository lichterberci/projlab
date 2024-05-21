package lab.proj.testUseCases;

import lab.proj.model.*;

/**
 * A class representing a specific use case with three rooms and two doors.
 */
public class ThreeRoomsTwoDoors implements TestUseCase {

    /**
     * Runs the use case, creating rooms, doors, and items.
     */
    protected Room r1;
    /**
     * Runs the use case, creating rooms, doors, and items.
     */
    protected Room r3;
    /**
     * Runs the use case, creating rooms, doors, and items.
     */
    protected Room r4;
    /**
     * Runs the use case, creating rooms, doors, and items.
     */
    protected Door d1;
    /**
     * Runs the use case, creating rooms, doors, and items.
     */
    protected Door d2;
    /**
     * Runs the use case, creating rooms, doors, and items.
     */
    protected Curse c1;
    /**
     * Runs the use case, creating rooms, doors, and items.
     */
    protected GasPoisoning g;
    /**
     * Runs the use case, creating rooms, doors, and items.
     */
    protected Towel t;
    /**
     * Runs the use case, creating rooms, doors, and items.
     */
    protected Mask m;

    /**
     * Runs the use case, creating rooms, doors, and items.
     */
    @Override
    public void runUseCase() {
        r1 = new Room();
        r3 = new Room();
        r4 = new Room();
        d1 = new Door();
        d2 = new Door();
        c1 = new Curse();
        g = new GasPoisoning();
        t = new Towel();
        m = new Mask();

        d1.SetRooms(r1, r3);

        d2.SetRooms(r1, r4);

        t.SetLocation(r1);

        m.SetLocation(r1);

        c1.SetLocation(r1);

        g.SetLocation(r1);
    }
}
