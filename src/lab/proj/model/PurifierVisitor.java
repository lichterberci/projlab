package lab.proj.model;

import java.util.Collections;

public class PurifierVisitor implements RoomEffectVisitor {

    public PurifierVisitor() {
        Logger.createObject(this);
    }

    public void VisitGasPoisoning(GasPoisoning gp) {
        Logger.invokeMethod(this, Collections.singletonList(gp));

        gp.location.GetActors().forEach(a -> a.incapacitated = false);
        gp.SetLocation(null);

        Logger.returnVoid();
    }

    public void VisitCurse(Curse c) {
        Logger.invokeMethod(this, Collections.singletonList(c));

        // Empty.

        Logger.returnVoid();
    }
}
