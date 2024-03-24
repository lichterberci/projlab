package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.Optional;

public class Gas implements TestUseCase {
    @Override
    public void runUseCase() {
        var room = new Room();
        var g = new GasPoisoning();
        var st = new Student();
        var mask = new Mask();
        var gprot = new GasProtection(mask);
        st.CollectItem(mask);
        mask.PickUp(st);
        st.AddCharge(gprot);
        st.SetLocation(room);
        room.AddEffect(g);
        g.SetLocation(room);
        IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, room, "room");
        IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, g, "gasPoisoning");
        IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, st, "student");
        IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, gprot, "gprot");
        IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER, mask, "mask");
        IndentedDebugPrinter.getInstance().invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, room, "TimePassed", Collections.emptyList());
        room.TimePassed();
        IndentedDebugPrinter.getInstance().returnFromMethod(IndentedDebugPrinter.CONTROLLER, room, "TimePassed", Optional.empty());
    }
}