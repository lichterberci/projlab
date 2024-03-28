package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;

/**
 * A class representing a CSE (Code of Studies and Exams) item in the game environment.
 * CSE items extend the functionality of living items.
 */
public class CSE extends LivingItem {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    public CSE() {
        Logger.invokeMethod(this, List.of());
        lifetime = 3;
    }

    public CSE(int lifetime) {
        Logger.invokeMethod(this, List.of(lifetime));
        this.lifetime = lifetime;
    }

    /**
     * Performs actions associated with the passage of time.
     * This method is currently empty for CSE items.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());        // No actions for CSE on time passage
    }

    @Override
    public void ApplyCharges() {
        Logger.invokeMethod(this, List.of());
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
        Logger.invokeMethod(this, List.of());
        lifetime--;
        if (lifetime == 0) {
            dead = true;
            this.Drop();
        }
    }

    public void SetLifeTime(int lifetime) {
        Logger.invokeMethod(this, List.of(lifetime));
        this.lifetime = lifetime;
    }
}
