package lab.proj.model;

import lab.proj.utils.DebugPrinter;
import lab.proj.utils.SequenceDiagramPrinter;

/**
 * An interface representing a visitor that visits room effects in the game environment.
 * This interface defines methods for visiting each type of room effect.
 */
public interface RoomEffectVisitor {

    /**
     * A logger for debugging purposes.
     */
    DebugPrinter Logger = SequenceDiagramPrinter.getInstance();

    /**
     * Defines the behavior of visiting a gas poisoning room effect.
     * @param gp The gas poisoning room effect being visited.
     */
    void VisitGasPoisoning(GasPoisoning gp);

    /**
     * Defines the behavior of visiting a curse room effect.
     * @param c The curse room effect being visited.
     */
    void VisitCurse(Curse c);
}
