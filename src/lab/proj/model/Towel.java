package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A class representing a towel item in the game environment.
 * Towels can be used and are subject to the passage of time.
 */
public class Towel extends LivingItem {

    private final TowelVisitor v;
    public Towel() {
        v = new TowelVisitor();
        IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.MAIN, v, "v");
    }
    /**
     * Performs actions associated with the passage of time for the towel.
     * This method is currently empty.
     */
    @Override
    public void TimePassed() {
        // Empty method
    }

    @Override
    public void ApplyCharges() {
        IndentedDebugPrinter.getInstance().invokeObjectMethod(actor, "GetLocation", List.of());
        Room r2 = actor.GetLocation();
        IndentedDebugPrinter.getInstance().returnFromMethod(actor, "GetLocation", Optional.of(r2));
        IndentedDebugPrinter.getInstance().invokeObjectMethod(r2, "VisitActors", List.of(v));
        r2.VisitActors(v);
        IndentedDebugPrinter.getInstance().returnFromMethod(r2, "VisitActors", Optional.empty());
    }

    /**
     * Specifies the use action for the towel.
     * This method is currently empty.
     */
    @Override
    public void Use() {
        // Empty method
    }
}
