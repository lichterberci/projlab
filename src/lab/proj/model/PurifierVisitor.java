package lab.proj.model;

import java.util.Collections;

/**
 * A class representing a visitor that visits room effects in the game environment.
 * This class implements the RoomEffectVisitor interface.
 */
public class PurifierVisitor implements RoomEffectVisitor {

    /**
     * Constructs a new purifier visitor.
     */
    public PurifierVisitor() {
        Logger.createObject(this);
    }

    /**
     * Defines the behavior of visiting a gas poisoning room effect.
     * This method visits actors in the location affected by the gas poisoning.
     *
     * @param gp The gas poisoning room effect being visited.
     */
    public void VisitGasPoisoning(GasPoisoning gp) {
        Logger.invokeMethod(this, Collections.singletonList(gp));

        gp.location.GetActors().forEach(a -> a.incapacitated = false);
        gp.SetLocation(null);

        Logger.returnVoid();
    }

    /**
     * Defines the behavior of visiting a curse room effect.
     * This method is currently empty.
     *
     * @param c The curse room effect being visited.
     */
    public void VisitCurse(Curse c) {
        Logger.invokeMethod(this, Collections.singletonList(c));

        // Empty.

        Logger.returnVoid();
    }
}
