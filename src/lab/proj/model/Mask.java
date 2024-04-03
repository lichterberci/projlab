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
     * Performs actions associated with the passage of time.
     * This method is currently empty for masks.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        // No actions for masks on time passage

        Logger.returnVoid();
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

        GasProtection gp = new GasProtection(this, 0);
        actor.AddGasProtection(gp);

        Logger.returnVoid();
    }
}
