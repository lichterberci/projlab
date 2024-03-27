package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * An abstract class representing an item in the game environment.
 * Items are entities that can be picked up, dropped, activated, and have a location.
 */
public abstract class Item implements Entity {

    /** A logger for debugging purposes. */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /** Indicates whether the item is activated. */
    protected boolean activated;

    /** Indicates whether the item is dead. */
    protected boolean dead;

    /** The room where the item is located. */
    protected Room location;

    /** The actor who picked up the item. */
    protected Actor actor;

    /**
     * Attempts to pick up the item with the specified actor.
     * @param a The actor attempting to pick up the item.
     * @return true if the item is successfully picked up, false otherwise.
     */
    public boolean PickUp(Actor a) {
        boolean result = AskTheUser.decision("Felvehető a tárgy?");
        if (result) {
            Logger.invokeObjectMethod(this, a, "CollectItem", List.of(this));
            a.CollectItem(this);
            Logger.returnFromMethod(this, a, "CollectItem", Optional.empty());

            actor = a;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Drops the item.
     */
    public void Drop() {
        Logger.invokeObjectMethod(this, actor, "DropItem", List.of(this));
        actor.DropItem(this);
        Logger.returnFromMethod(this, actor, "DropItem", Optional.empty());

        actor = null;
    }

    /**
     * Sets the location of the item.
     * @param location The new location of the item.
     */
    public void SetLocation(Room location) {
        this.location = location;
    }

    /**
     * Checks if the item is currently picked up by an actor.
     * @return true if the item is picked up, false otherwise.
     */
    public boolean IsPickedUp() {
        return actor != null;
    }

    /**
     * Activates the item.
     * This method typically applies some effect associated with the item.
     */
    public void Activate() {
        activated = true;
        var gp = new GasPoisoning();
        Logger.createObject(this, gp, "gp");

        Logger.invokeObjectMethod(this, actor, "GetLocation", List.of());
        Room room = actor.GetLocation();
        Logger.returnFromMethod(this, actor, "GetLocation", Optional.of(room));

        Logger.invokeObjectMethod(this, room, "AddEffect", List.of(gp));
        room.AddEffect(gp);
        Logger.returnFromMethod(this, room, "AddEffect", Optional.empty());

        Logger.invokeObjectMethod(this, this, "Drop", Collections.emptyList());
        Drop();
        Logger.returnFromMethod(this, this, "Drop", Optional.empty());
    }

    /**
     * Checks if the item is activated.
     * @return true if the item is activated, false otherwise.
     */
    public boolean IsActivated() {
        return activated;
    }

    /**
     * Applies charges associated with the item.
     * This method is typically overridden by subclasses to define specific behaviors.
     */
    public void ApplyCharges() {
        // Default implementation does nothing
    }
}
