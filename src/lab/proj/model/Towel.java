package lab.proj.model;

import java.util.List;

/**
 * A class representing a towel item in the game environment.
 * Towels can be used and are subject to the passage of time.
 */
public class Towel extends LivingItem {

    private final TowelVisitor tv;

    public Towel() {
        Logger.createObject(this);
        tv = new TowelVisitor();
    }

    @Override
    public void ApplyCharges() {
        Logger.invokeMethod(this, List.of());

        if (fake) {
            Logger.returnVoid();
            return;
        }

        Room r2 = actor.GetLocation();
        r2.VisitActors(tv);

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

    @Override
    public void VisitItem(ItemVisitor iv) {
        iv.VisitTowel(this);
    }
}
