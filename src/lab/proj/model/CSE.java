package lab.proj.model;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a CSE (Code of Studies and Exams) item in the game environment.
 * CSE items extend the functionality of living items.
 */
public class CSE extends LivingItem {

    /**
     * The lifetime of the CSE item.
     */
    public CSE() {
        this(3);
    }

    /**
     * The lifetime of the CSE item.
     */
    public CSE(int lifetime) {
        Logger.createObject(this);
        this.lifetime = lifetime;
    }

    /**
     * Performs actions associated with the passage of time.
     * This method is currently empty for CSE items.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        // Overrides LivingItem's TimePassed implementation.

        Logger.returnVoid();
    }

    /**
     * Applies charges associated with the CSE item.
     * This method adds drop-out protection for the associated actor.
     */
    @Override
    public void ApplyCharges() {
        Logger.invokeMethod(this, List.of());

        if (!activated || fake) {
            Logger.returnVoid();
            return;
        }
        for (int i = 0; i < lifetime; i++) {
            DropOutProtection dp1 = new DropOutProtection(this, 1);
            actor.AddDropOutProtection(dp1);
        }

        Logger.returnVoid();
    }

    /**
     * Performs the action of using the CSE item.
     */
    @Override
    public void Use() {
        Logger.invokeMethod(this, List.of());

        Age();

        Logger.returnVoid();
    }

    /**
     * Ages the CSE item.
     */
    public void SetLifeTime(int lifetime) {
        Logger.invokeMethod(this, Collections.singletonList(lifetime));

        this.lifetime = lifetime;

        Logger.returnVoid();
    }

    /**
     * Visits the CSE item.
     */
    @Override
    public void VisitItem(ItemVisitor iv) {
        iv.VisitCSE(this);
    }
}
