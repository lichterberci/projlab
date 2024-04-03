package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Random;

/**
 * A class representing a beer mug item in the game environment.
 * Beer mugs extend the functionality of living items.
 */
public class BeerMug extends LivingItem {

    private static final Random random = new Random();

    /**
     * A logger for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    public BeerMug() {
        Logger.createObject(this);
    }

    /**
     * Performs actions associated with the passage of time.
     * This method is currently empty for beer mugs.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());        // No actions for beer mug on time passage

        Logger.returnVoid();
    }

    /**
     * Applies charges associated with the beer mug.
     * This method adds a drop out protection for the associated actor.
     */
    @Override
    public void ApplyCharges() {
        Logger.invokeMethod(this, List.of());

        if (!activated)
            return;

        DropOutProtection dp1 = new DropOutProtection(this, 0);
        actor.AddDropOutProtection(dp1);

        Logger.returnVoid();
    }

    /**
     * Performs the action of using the beer mug.
     * This method adds a drop out protection for the associated actor.
     */
    @Override
    public void Use() {
        Logger.invokeMethod(this, List.of());

        DropOutProtection dp2 = new DropOutProtection(this, 0);
        actor.AddDropOutProtection(dp2);

        int id = random.nextInt(0, actor.GetItems().size());
        actor.GetItems().get(id).Drop();

        Logger.returnVoid();
    }
}
