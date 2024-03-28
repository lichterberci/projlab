package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.IndentedDebugPrinter;

public class FourRoomsThreeDoors implements TestUseCase {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();
    protected Room r1;
    protected Room r2;
    protected Room r3;
    protected Room r4;
    protected Door d1;
    protected Door d2;
    protected Door d3;
    protected Curse c1;
    protected GasPoisoning g;
    protected Towel t;
    protected Mask m;

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
        r1.AddItem(t);
        r1.AddItem(m);
        r2.AddEffect(c1);
        r1.AddEffect(g);
    }
}

