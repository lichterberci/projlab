package lab.proj.model;

import java.util.List;
import java.util.Optional;

/**
 * A class representing a mask item in the game environment.
 * Masks extend the functionality of living items.
 */
public class Mask extends LivingItem {

    /**
     * Performs actions associated with the passage of time.
     * This method is currently empty for masks.
     */
    @Override
    public void TimePassed() {
        
        // No actions for masks on time passage
    }

    /**
     * Performs the action of using the mask.
     * This method is currently empty for masks.
     */
    @Override
    public void Use() {
        
        // No specific action for using masks
    }

    @Override
    public void ApplyCharges() {
        
        if (!activated) {
            return;
        }
        GasProtection gp = new GasProtection(this, 0);

        Logger.createObject(gp, "gp");

        
        actor.AddGasProtection(gp);
        
    }
}
