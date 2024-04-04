package lab.proj.model;

import lab.proj.utils.SequenceDiagramPrinter;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a CSE (Code of Studies and Exams) item in the game environment.
 * CSE items extend the functionality of living items.
 */
public class CSE extends LivingItem {
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    public CSE() {
        this(3);
    }

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
        Logger.invokeMethod(this, List.of());        // No actions for CSE on time passage
        Logger.returnVoid();
    }

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
     * This method is currently empty for CSE items.
     */
    @Override
    public void Use() {
        Logger.invokeMethod(this, List.of());
        if (fake) {
            Logger.returnVoid();
            return;
        }
        lifetime--;
        if (lifetime == 0) {
            dead = true;
            Drop();
        }

        Logger.returnVoid();
    }

    public void SetLifeTime(int lifetime) {
        Logger.invokeMethod(this, Collections.singletonList(lifetime));

        this.lifetime = lifetime;

        Logger.returnVoid();
    }
}
