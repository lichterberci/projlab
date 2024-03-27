package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.Optional;
import java.util.List;

public class Gas implements TestUseCase {

    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
            var room = new Room();
        var g = new GasPoisoning();
        var st = new Student();
        var mask = new Mask();
        var gprot = new GasProtection(mask, 0);
        Logger.createObject(IndentedDebugPrinter.MAIN, room, "room");
        Logger.createObject(IndentedDebugPrinter.MAIN, g, "gasPoisoning");
        Logger.createObject(IndentedDebugPrinter.MAIN, st, "student");
        Logger.createObject(IndentedDebugPrinter.MAIN, gprot, "gprot");
        Logger.createObject(IndentedDebugPrinter.MAIN, mask, "mask");

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, st, "SetLocation", List.of(room));
        st.SetLocation(room);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, st, "SetLocation", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, st, "CollectItem", List.of(mask));
        st.CollectItem(mask);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, st, "CollectItem", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, mask, "PickUp", List.of(st));
        mask.PickUp(st);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, mask, "PickUp", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, room, "AddEffect", List.of(g));
        room.AddEffect(g);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, room, "AddEffect", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, g, "SetLocation", List.of(room));
        g.SetLocation(room);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, g, "SetLocation", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, room, "TimePassed", Collections.emptyList());
        room.TimePassed();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, room, "TimePassed", Optional.empty());
    
    }
}
