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
        Logger.invokeObjectMethod(room, "TimePassed", Collections.emptyList());
        room.TimePassed();
        Logger.returnFromMethod(room, "TimePassed", Optional.empty());
    }
}
