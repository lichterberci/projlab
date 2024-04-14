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

    public BeerMug() {
        Logger.createObject(this);
    }

    /**
     * Applies charges associated with the beer mug.
     * This method adds drop-out protection for the associated actor.
     */
    @Override
    public void ApplyCharges() {
        Logger.invokeMethod(this, List.of());

        if (fake || !activated || actor == null) {
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

        Item item = actor != null ? Randomware.Choice(actor.GetItems()) : null;
        UseWithSpecificDropCandidate(item);

        Logger.returnVoid();
    }

    public void UseWithSpecificDropCandidate(Item dropCandidate) {
        Logger.invokeMethod(this, Collections.singletonList(dropCandidate));

        if (fake || actor == null || dropCandidate == null) {
            Logger.returnVoid();
            return;
        }

        dropCandidate.Drop();

        DropOutProtection dop2 = new DropOutProtection(this, 0);
        actor.AddDropOutProtection(dop2);

        Logger.returnVoid();
    }
}
