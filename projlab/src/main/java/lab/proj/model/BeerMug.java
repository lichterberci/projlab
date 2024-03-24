package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

/**
 * A class representing a beer mug item in the game environment.
 * Beer mugs extend the functionality of living items.
 */
public class BeerMug extends LivingItem {

    /** A logger for debugging purposes. */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * Performs actions associated with the passage of time.
     * This method is currently empty for beer mugs.
     */
    @Override
    public void TimePassed() {
        // No actions for beer mug on time passage
    }

    /**
     * Applies charges associated with the beer mug.
     * This method adds a drop out protection for the associated actor.
     */
    @Override
    public void ApplyCharges() {
        DropOutProtection dp1 = new DropOutProtection(this, 0);

        Logger.createObject(this, dp1, "dp1");

        Logger.invokeObjectMethod(this, actor, "AddDropOutProtection", List.of(dp1));
        actor.AddDropOutProtection(dp1);
        Logger.returnFromMethod(this, actor, "AddDropOutProtection", Optional.empty());
    }

    /**
     * Performs the action of using the beer mug.
     * This method adds a drop out protection for the associated actor.
     */
    @Override
    public void Use() {
        DropOutProtection dp2 = new DropOutProtection(this, 0);
        Logger.createObject(this, dp2, "dp2");

        Logger.invokeObjectMethod(this, actor, "AddDropOutProtection", List.of(dp2));
        actor.AddDropOutProtection(dp2);
        Logger.returnFromMethod(this, actor, "AddDropOutProtection", Optional.empty());
    }
}
