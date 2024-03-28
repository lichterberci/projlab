package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

/**
 * A class representing a CSE (Code of Studies and Exams) item in the game environment.
 * CSE items extend the functionality of living items.
 */
public class CSE extends LivingItem {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    public CSE() {
        
        lifetime = 3;
    }

    public CSE(int lifetime) {
        
        this.lifetime = lifetime;
    }

    /**
     * Performs actions associated with the passage of time.
     * This method is currently empty for CSE items.
     */
    @Override
    public void TimePassed() {
        
        // No actions for CSE on time passage
    }

    @Override
    public void ApplyCharges() {
        if (!activated) {
            return;
        }
        for (int i = 0; i < lifetime; i++) {
            DropOutProtection dp1 = new DropOutProtection(this, 1);
            Logger.createObject(dp1, "dpcse" + i);
            actor.AddDropOutProtection(dp1);
        }
    }

    /**
     * Performs the action of using the CSE item.
     * This method is currently empty for CSE items.
     */
    @Override
    public void Use() {
        lifetime--;
        if (lifetime == 0) {
            dead = true;
            this.Drop();
        }
    }

    public void SetLifeTime(int lifetime) {
        this.lifetime = lifetime;
    }
}