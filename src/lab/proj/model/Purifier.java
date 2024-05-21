package lab.proj.model;

import java.util.List;

/**
 * A class representing a purifier item in the game environment.
 * Purifiers can affect the behavior of the associated room in the game environment.
 */
public class Purifier extends Item {

    /**
     * Creates a new purifier item.
     */
    public Purifier() {
        Logger.createObject(this);
    }

    /**
     * Activates the purifier.
     * This method drops the purifier and visits the effects of the associated room.
     */
    @Override
    public void Activate() {
        Logger.invokeMethod(this, List.of());

        if (fake) {
            Logger.returnVoid();
            return;
        }

        activated = true;

        Drop();
        location.VisitEffects(new PurifierVisitor());
        dead = true;

        Logger.returnVoid();
    }

    /**
     * Visits the purifier item.
     *
     * @param iv The visitor to visit the item.
     */
    @Override
    public void VisitItem(ItemVisitor iv) {
        iv.VisitPurifier(this);
    }
}
