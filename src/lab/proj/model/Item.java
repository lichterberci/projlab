package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.List;

/**
 * An abstract class representing an item in the game environment.
 * Items are entities that can be picked up, dropped, activated, and have a location.
 */
public abstract class Item implements Entity {

    /**
     * A logger for debugging purposes.
     */
    protected static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * Indicates whether the item is activated.
     */
    protected boolean activated;

    /**
     * Indicates whether the item is dead.
     */
    protected boolean dead;

    /**
     * The room where the item is located.
     */
    protected Room location;

    /**
     * The actor who picked up the item.
     */
    protected Actor actor;

    /**
     * Attempts to pick up the item with the specified actor.
     *
     * @param a The actor attempting to pick up the item.
     * @return true if the item is successfully picked up, false otherwise.
     */
    public boolean PickUp(Actor a) {
        Logger.invokeMethod(this, Collections.singletonList(a));

        boolean canBePickedUp = AskTheUser.decision("Felvehető a tárgy?");
        if (canBePickedUp) {
            a.CollectItem(this);
            actor = a;
        }

        Logger.returnValue(canBePickedUp);
        return canBePickedUp;
    }

    /**
     * Drops the item.
     */
    public void Drop() {
        Logger.invokeMethod(this, List.of());

        actor.DropItem(this);
        actor = null;

        Logger.returnVoid();
    }

    /**
     * Sets the location of the item.
     *
     * @param location The new location of the item.
     */
    public void SetLocation(Room location) {
        Logger.invokeMethod(this, Collections.singletonList(location));

        this.location = location;

        Logger.returnVoid();
    }

    /**
     * Checks if the item is currently picked up by an actor.
     *
     * @return true if the item is picked up, false otherwise.
     */
    public boolean IsPickedUp() {
        Logger.invokeMethod(this, List.of());

        boolean pickedUp = actor != null;

        Logger.returnValue(pickedUp);
        return pickedUp;
    }

    /**
     * Activates the item.
     * This method typically applies some effect associated with the item.
     */
    public void Activate() {
        Logger.invokeMethod(this, List.of());
        activated = true;
        Logger.returnVoid();
    }

    /**
     * Checks if the item is activated.
     *
     * @return true if the item is activated, false otherwise.
     */
    public boolean IsActivated() {
        Logger.invokeMethod(this, List.of());
        Logger.returnValue(activated);
        return activated;
    }

    /**
     * Applies charges associated with the item.
     * This method is typically overridden by subclasses to define specific behaviors.
     */
    public void ApplyCharges() {
        Logger.invokeMethod(this, List.of());

        // Default implementation does nothing

        Logger.returnVoid();
    }
}
