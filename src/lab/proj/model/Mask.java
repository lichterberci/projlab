package lab.proj.model;

import java.util.List;

/**
 * A class representing a mask item in the game environment.
 * Masks extend the functionality of living items.
 */
public class Mask extends LivingItem {

    public Mask() {
        Logger.createObject(this);
    }

    /**
     * Performs the action of using the mask.
     * This method is currently empty for masks.
     */
    @Override
    public void Use() {
        Logger.invokeMethod(this, List.of());

        // No specific action for using masks

        Logger.returnVoid();
    }

    @Override
    public void ApplyCharges() {
        Logger.invokeMethod(this, List.of());

        if (!activated || fake) {
            Logger.returnVoid();
            return;
        }

        var gp = new GasProtection(this, 0);
        actor.AddGasProtection(gp);

        Logger.returnVoid();
    }

    @Override
    public void VisitItem(ItemVisitor iv) {
        iv.VisitMask(this);
    }
}
