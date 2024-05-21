package lab.proj.model;

import java.util.List;

/**
 * A class representing a towel item in the game environment.
 * Towels can be used and are subject to the passage of time.
 */
public class Towel extends LivingItem {

    /**
     * The visitor for the towel item.
     */
    private final TowelVisitor tv;

    /**
     * Creates a new towel item.
     */
    public Towel() {
        Logger.createObject(this);
        tv = new TowelVisitor();
    }

    /**
     * Applies the charges associated with the towel.
     * This method visits the actors in the room of the actor using the towel.
     */
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

    /**
     * Visits the towel item.
     *
     * @param iv The visitor to visit the item.
     */
    @Override
    public void VisitItem(ItemVisitor iv) {
        iv.VisitTowel(this);
    }

    @Override
    public boolean PickUp(Actor a) {
        super.PickUp(a);
        activated = true;
    }

    @Override
    public void Drop() {
        super.Drop();
        activated = false;
    }
}
