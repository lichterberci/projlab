package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;

/**
 * A class representing a gas poisoning room effect in the game environment.
 * Gas poisoning effects extend the functionality of room effects.
 */
public class GasPoisoning extends RoomEffect {

    /**
     * A logger for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();
    /**
     * The remaining lifetime of the gas poisoning effect.
     */
    private int lifetime;

    /**
     * Performs actions associated with the passage of time for the gas poisoning effect.
     * This method visits actors in the location affected by the gas poisoning.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        var gv = new GasVisitor();
        Logger.createObject(gv, "gv");
        location.VisitActors(gv);

        Logger.returnVoid();
    }
}
