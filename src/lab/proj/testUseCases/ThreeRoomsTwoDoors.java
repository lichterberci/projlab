package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class ThreeRoomsTwoDoors implements TestUseCase {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();
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

        Logger.createObject(r1, "r1");
        Logger.createObject(r3, "r3");
        Logger.createObject(r4, "r4");
        Logger.createObject(d1, "d1");
        Logger.createObject(d2, "d2");
        Logger.createObject(c1, "c1");
        Logger.createObject(g, "g");
        Logger.createObject(t, "t");
        Logger.createObject(m, "m");

        d1.SetRooms(r1, r3);

        d2.SetRooms(r1, r4);

        r1.AddItem(t);

        r1.AddItem(m);

        r1.AddEffect(c1);

        r1.AddEffect(g);
    }
}
