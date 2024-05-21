package lab.proj.testUseCases;

import lab.proj.model.*;

/**
 * A class representing a specific use case with four rooms and three doors.
 */
public class FourRoomsThreeDoors implements TestUseCase {

    /**
     * The rooms and doors in the use case.
     */
    protected Room r1;
    /**
     * The rooms and doors in the use case.
     */
    protected Room r2;
    /**
     * The rooms and doors in the use case.
     */
    protected Room r3;
    /**
     * The rooms and doors in the use case.
     */
    protected Room r4;
    /**
     * The rooms and doors in the use case.
     */
    protected Door d1;
    /**
     * The rooms and doors in the use case.
     */
    protected Door d2;
    /**
     * The rooms and doors in the use case.
     */
    protected Door d3;
    /**
     * The rooms and doors in the use case.
     */
    protected Curse c1;
    /**
     * The rooms and doors in the use case.
     */
    protected GasPoisoning g;
    /**
     * The rooms and doors in the use case.
     */
    protected Towel t;
    /**
     * The rooms and doors in the use case.
     */
    protected Mask m;

    /**
     * Runs the use case, creating the rooms and doors and setting the items in the rooms.
     */
    @Override
    public void runUseCase() {
        r1 = new Room();
        r2 = new Room();
        r3 = new Room();
        r4 = new Room();
        d1 = new Door();
        d2 = new Door();
        d3 = new Door();
        c1 = new Curse();
        g = new GasPoisoning();
        t = new Towel();
        m = new Mask();

        d3.SetRooms(r1, r2);
        d1.SetRooms(r1, r3);
        d2.SetRooms(r2, r4);
        t.SetLocation(r1);
        m.SetLocation(r1);
        c1.SetLocation(r2);
        g.SetLocation(r1);
    }
}

