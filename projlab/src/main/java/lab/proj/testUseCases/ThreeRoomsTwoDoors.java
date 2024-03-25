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

        Logger.createObject(IndentedDebugPrinter.MAIN, r1, "r1");
        Logger.createObject(IndentedDebugPrinter.MAIN, r3, "r3");
        Logger.createObject(IndentedDebugPrinter.MAIN, r4, "r4");
        Logger.createObject(IndentedDebugPrinter.MAIN, d1, "d1");
        Logger.createObject(IndentedDebugPrinter.MAIN, d2, "d2");
        Logger.createObject(IndentedDebugPrinter.MAIN, c1, "c1");
        Logger.createObject(IndentedDebugPrinter.MAIN, g, "g");
        Logger.createObject(IndentedDebugPrinter.MAIN, t, "t");
        Logger.createObject(IndentedDebugPrinter.MAIN, m, "m");

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, d1, "SetRooms", List.of(r1, r3));
        d1.SetRooms(r1, r3);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, d1, "SetRooms", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, d2, "SetRooms", List.of(r1, r4));
        d2.SetRooms(r1, r4);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, d2, "SetRooms", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, r1, "AddItem", List.of(t));
        r1.AddItem(t);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, r1, "AddItem", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, r1, "AddItem", List.of(m));
        r1.AddItem(m);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, r1, "AddEffect", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, r1, "AddEffect", List.of(c1));
        r1.AddEffect(c1);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, r1, "AddEffect", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, r1, "AddEffect", List.of(g));
        r1.AddEffect(g);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, r1, "AddEffect", Optional.empty());
    }
}
