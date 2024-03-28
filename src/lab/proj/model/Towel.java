package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

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
        IndentedDebugPrinter.getInstance().createObject(v, "v");
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
        Room r2 = actor.GetLocation();
        r2.VisitActors(v);
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
