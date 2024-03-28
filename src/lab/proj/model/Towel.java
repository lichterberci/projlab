package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;

/**
 * A class representing a towel item in the game environment.
 * Towels can be used and are subject to the passage of time.
 */
public class Towel extends LivingItem {

    private final TowelVisitor v;

    public Towel() {
        Logger.invokeMethod(this, List.of());
        v = new TowelVisitor();
        IndentedDebugPrinter.getInstance().createObject(v, "v");
    }

    /**
     * Performs actions associated with the passage of time for the towel.
     * This method is currently empty.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        // Empty method

        Logger.returnVoid();
    }

    @Override
    public void ApplyCharges() {
        Logger.invokeMethod(this, List.of());

        Room r2 = actor.GetLocation();
        r2.VisitActors(v);

        Logger.returnVoid();
    }

    /**
     * Specifies the use action for the towel.
     * This method is currently empty.
     */
    @Override
    public void Use() {
        Logger.invokeMethod(this, List.of());

        // Empty method

        Logger.returnVoid();
    }
}
