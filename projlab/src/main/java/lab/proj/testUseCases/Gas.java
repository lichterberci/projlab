package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Collections;
import java.util.Optional;

public class Gas implements TestUseCase {

    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        var room = new Room();
        var g = new GasPoisoning();
        var st = new Student();
        var mask = new Mask();
        var t1 = new Teacher();
        var gprot = new GasProtection(mask, 0);
        Logger.createObject(IndentedDebugPrinter.MAIN, room, "room");
        Logger.createObject(IndentedDebugPrinter.MAIN, g, "gasPoisoning");
        Logger.createObject(IndentedDebugPrinter.MAIN, st, "student");
        Logger.createObject(IndentedDebugPrinter.MAIN, gprot, "gprot");
        Logger.createObject(IndentedDebugPrinter.MAIN, mask, "mask");
        Logger.createObject(IndentedDebugPrinter.MAIN, t1, "t1");

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, st, "SetLocation", List.of(room));
        st.SetLocation(room);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, st, "SetLocation", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t1, "SetLocation", List.of(room));
        t1.SetLocation(room);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t1, "SetLocation", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, mask, "PickUp", List.of(st));
        boolean result = mask.PickUp(st);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, mask, "PickUp", Optional.of(result));

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, mask, "Activate", List.of(st));
        mask.Activate();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, mask, "Active", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, room, "AddEffect", List.of(g));
        room.AddEffect(g);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, room, "AddEffect", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, g, "SetLocation", List.of(room));
        g.SetLocation(room);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, g, "SetLocation", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, st, "TimePassed", Collections.emptyList());
        st.TimePassed();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, st, "TimePassed", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, room, "TimePassed", Collections.emptyList());
        room.TimePassed();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, room, "TimePassed", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t1, "DropOutAll", Collections.emptyList());
        t1.DropOutAll();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t1, "DropOutAll", Optional.empty());
    }
}
