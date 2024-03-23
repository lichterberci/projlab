package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class GasPoisoning extends RoomEffect {
    private int lifetime;

    @Override
    public void TimePassed() {
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, location, "VisitActors", List.of(this));
        var gv = new GasVisitor();
        IndentedDebugPrinter.getInstance().createObject(this, gv, "gv");
        location.VisitActors(gv);
        IndentedDebugPrinter.getInstance().returnFromMethod(this, location, "VisitActors", Optional.empty());
    }
}
