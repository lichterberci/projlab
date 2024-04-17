package lab.proj.model;

import lab.proj.utils.DebugPrinter;
import lab.proj.utils.SequenceDiagramPrinter;

public interface RoomEffectVisitor {

    /**
     * A logger for debugging purposes.
     */
    DebugPrinter Logger = SequenceDiagramPrinter.getInstance();

    void VisitGasPoisoning(GasPoisoning gp);

    void VisitCurse(Curse c);
}
