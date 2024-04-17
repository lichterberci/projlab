package lab.proj.testUseCases;

import lab.proj.model.*;

public class ThreeRoomsTwoDoors implements TestUseCase {

    protected Room r1;
    protected Room r3;
    protected Room r4;
    protected Door d1;
    protected Door d2;
    protected Curse c1;
    protected GasPoisoning g;
    protected Towel t;
    protected Mask m;

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
