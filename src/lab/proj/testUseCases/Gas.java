package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.IndentedDebugPrinter;

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
        Logger.createObject(room, "room");
        Logger.createObject(g, "gasPoisoning");
        Logger.createObject(st, "student");
        Logger.createObject(gprot, "gprot");
        Logger.createObject(mask, "mask");
        room.TimePassed();
    }
}
