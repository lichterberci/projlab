package lab.proj.model;

import lab.proj.utils.SequenceDiagramPrinter;

import java.util.Collections;

public class PurifierVisitor implements RoomEffectVisitor {
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    public PurifierVisitor() {
        Logger.createObject(this);
    }

    public void VisitGasPoisoning(GasPoisoning gp) {
        Logger.invokeMethod(this, Collections.singletonList(gp));

        gp.location.RemoveEffect(gp);

        Logger.returnVoid();
    }

    public void VisitCurse(Curse c) {
        Logger.invokeMethod(this, Collections.singletonList(c));

        // Empty.

        Logger.returnVoid();
    }
}
