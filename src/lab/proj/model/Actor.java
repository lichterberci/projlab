package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An abstract class representing an actor in the game environment.
 * This class implements the basic functionalities and properties of an actor.
 */
public abstract class Actor implements Entity {
    /**
     * A logger for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * Indicates whether the actor is incapacitated or not.
     */
    protected boolean incapacitated;

    /**
     * The room where the actor is currently located.
     */
    protected Room location;

    /**
     * The items collected by the actor.
     */
    protected List<Item> collectedItems = new ArrayList<>();

    /**
     * Gas protections possessed by the actor.
     */
    protected List<GasProtection> gasProtections = new ArrayList<>();

    /**
     * Drop out protections possessed by the actor.
     */
    protected List<DropOutProtection> dropOutProtections = new ArrayList<>();

    /**
     * Attempts to use a door to move to another room.
     *
     * @param d The door to be used.
     * @return true if the actor successfully goes through the door, false otherwise.
     */
    public boolean UseDoor(Door d) {
        boolean wasSuccessful = d.GoThrough(location, this);
        if (wasSuccessful) {
            List<Room> rooms = d.GetRooms();
            Room prevRoom = null;
            if (rooms.get(0) == location) {
                prevRoom = rooms.get(1);
            } else {
                prevRoom = rooms.get(0);
            }
            
            prevRoom.StepOut(this);
        }

        return wasSuccessful;
    }

    /**
     * Collects an item from the current location and adds it to the actor's collection.
     *
     * @param i The item to be collected.
     */
    public void CollectItem(Item i) {
        location.RemoveItem(i);
        collectedItems.add(i);
    }

    /**
     * Drops an item from the actor's collection into the current location.
     *
     * @param i The item to be dropped.
     */
    public void DropItem(Item i) {
        location.AddItem(i);
        Logger.returnVoid();
    }

    /**
     * Adds a drop out protection to the actor.
     *
     * @param dropOutProtection The drop out protection to be added.
     */
    public void AddDropOutProtection(DropOutProtection dropOutProtection) {
        dropOutProtections.add(dropOutProtection);
    }

    /**
     * Adds a gas protection to the actor.
     *
     * @param gasProtection The gas protection to be added.
     */
    public void AddGasProtection(GasProtection gasProtection) {
        gasProtections.add(gasProtection);
    }

    /**
     * Retrieves the items collected by the actor.
     *
     * @return The list of collected items.
     */
    public List<Item> GetItems() {
        return collectedItems;
    }

    /**
     * Retrieves the room where the actor is currently located.
     *
     * @return The current location of the actor.
     */
    public Room GetLocation() {
        return location;
    }

    /**
     * Sets the location of the actor to the specified room.
     *
     * @param r The room to set as the actor's location.
     */
    public void SetLocation(Room r) {
        location = r;
        r.AddActor(this);

        Logger.returnVoid();
    }

    /**
     * Accepts a visitor for visiting the actor.
     *
     * @param v The visitor to visit the actor.
     */
    public abstract void VisitActor(ActorVisitor v);

    /**
     * Performs an action representing the actor being shocked.
     */
    public abstract void Shock();

    /**
     * Performs an action representing the actor dropping out.
     */
    public abstract void DropOut();

    /**
     * Checks if the actor is currently blocked (incapacitated).
     *
     * @return true if the actor is blocked, false otherwise.
     */
    public boolean IsBlocked() {
        
        return incapacitated;
    }
}
