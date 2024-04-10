package lab.proj.model;

import lab.proj.utils.Randomware;
import lab.proj.utils.SequenceDiagramPrinter;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a beer mug item in the game environment.
 * Beer mugs extend the functionality of living items.
 */
public class BeerMug extends LivingItem {

    /**
     * A logger for debugging purposes.
     */
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

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
     * This method adds drop-out protection for the associated actor.
     */
    @Override
    public void ApplyCharges() {
        Logger.invokeMethod(this, List.of());

        if (!activated || fake) {
            Logger.returnVoid();
            return;
        }

        DropOutProtection dp1 = new DropOutProtection(this, 0);
        actor.AddDropOutProtection(dp1);

        Logger.returnVoid();
    }

    /**
     * Performs the action of using the beer mug.
     * This method adds drop-out protection for the associated actor.
     */
    @Override
    public void Use() {
        Logger.invokeMethod(this, List.of());

        Item item = Randomware.Choice(actor.GetItems());
        UseWithSpecificDropCandidate(item);

        Logger.returnVoid();
    }

    public void UseWithSpecificDropCandidate(Item dropCandidate) {
        Logger.invokeMethod(this, Collections.singletonList(dropCandidate));

        if (fake) {
            Logger.returnVoid();
            return;
        }

        dropCandidate.Drop();

        if (actor != null) {
            DropOutProtection dop2 = new DropOutProtection(this, 0);
            actor.AddDropOutProtection(dop2);
        }

        Logger.returnVoid();
    }
}
