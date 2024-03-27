package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A class representing a room in the game environment.
 * Rooms can contain actors, items, and room effects.
 */
public class Room implements Entity {

    /** A logger for debugging purposes. */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /** The actors currently inside the room. */
    private final List<Actor> actorsInside = new ArrayList<>();

    /** The items on the floor of the room. */
    private final List<Item> itemsOnTheFloor = new ArrayList<>();

    /** The active effects present in the room. */
    private final List<RoomEffect> activeEffects = new ArrayList<>();

    /** The doors connected to the room. */
    private final List<Door> doors = new ArrayList<>();

    /** The capacity of the room. */
    private int capacity;

    /**
     * Adds an actor to the room.
     * @param actor The actor to be added.
     */
    public void AddActor(Actor actor) {
        actorsInside.add(actor);
    }

    /**
     * Removes an actor from the room.
     * @param actor The actor to be removed.
     */
    public void RemoveActor(Actor actor) {
        actorsInside.remove(actor);
    }

    /**
     * Checks if the room is at full capacity.
     * @return true if the room is full, false otherwise.
     */
    public boolean IsFull() {
        boolean roomIsFull = AskTheUser.decision("Tele van a szoba?");
        return roomIsFull;
    }

    /**
     * Allows an actor to step into the room.
     * @param a The actor attempting to step into the room.
     * @return true if the actor successfully steps into the room, false otherwise.
     */
    public boolean StepIn(Actor a) {
        if (IsFull())
            return false;

        a.SetLocation(this);
        return true;
    }

    /**
     * Allows an actor to step out of the room.
     * @param a The actor attempting to step out of the room.
     */
    public void StepOut(Actor a) {
        actorsInside.remove(a);
    }

    /**
     * Retrieves the doors connected to the room.
     * @return A list of doors connected to the room.
     */
    public List<Door> GetDoors() {
        return doors;
    }

    /**
     * Retrieves the items on the floor of the room.
     * @return A list of items on the floor of the room.
     */
    public List<Item> GetItems() {
        return itemsOnTheFloor;
    }

    /**
     * Adds an item to the floor of the room.
     * @param i The item to be added.
     */
    public void AddItem(Item i) {
        Logger.invokeObjectMethod(this, i, "SetLocation", Collections.singletonList(this));
        i.SetLocation(this);
        Logger.returnFromMethod(this, i, "SetLocation", Optional.empty());
        itemsOnTheFloor.add(i);
    }

    /**
     * Removes an item from the floor of the room.
     * @param i The item to be removed.
     */
    public void RemoveItem(Item i) {
        Logger.invokeObjectMethod(this, i, "SetLocation", Collections.singletonList(null));
        i.SetLocation(null);
        Logger.returnFromMethod(this, i, "SetLocation", Optional.empty());
        itemsOnTheFloor.remove(i);
    }

    /**
     * Merges the room with another room.
     */
    public void Merge() {
        if (!actorsInside.isEmpty())
            return;

        Room r2 = null;
        for (Door door : doors) {
            List<Room> doorRooms = door.GetRooms();
            Room otherRoom = (doorRooms.get(0) == this) ? doorRooms.get(doorRooms.size()-1) : doorRooms.get(0);
            boolean chooseThis = AskTheUser.decision(String.format("A %s szobával egyesüljön?", Logger.getObjectName(otherRoom)));
            if (chooseThis) {
                r2 = otherRoom;
                break;
            }
        }

        if (r2 == null || !r2.actorsInside.isEmpty())
            return;

        CopyOnWriteArrayList<Item> otherRoomsItems = new CopyOnWriteArrayList<>(r2.itemsOnTheFloor);
        for (Item item : otherRoomsItems) {
            Logger.invokeObjectMethod(this, r2, "RemoveItem", List.of(item));
            r2.RemoveItem(item);
            Logger.returnFromMethod(this, r2, "RemoveItem", Optional.empty());
            Logger.selfInvokeMethod(this, "AddItem", List.of(item));
            AddItem(item);
            Logger.returnFromMethod(this, this, "AddItem", Optional.empty());
        }

        capacity = r2.capacity;

        Logger.invokeObjectMethod(this, doors.get(doors.size()-1), "ChangeRoom", List.of(r2, this));
        // fake it till you make it
        Logger.returnFromMethod(this, doors.get(doors.size()-1), "ChangeRoom", Optional.empty());

        for (Door door : doors) {
            Logger.invokeObjectMethod(this, door, "Show", List.of());
            door.Show();
            Logger.returnFromMethod(this, door, "Show", Optional.empty());
        }

        Logger.destroyObject(this, r2);
    }

    /**
     * Splits the room into two separate rooms.
     */
    public void Split() {
        var r2 = new Room();
        Logger.createObject(this, r2, "r2");

        CopyOnWriteArrayList<Item> currentItems = new CopyOnWriteArrayList<>(itemsOnTheFloor);
        for (Item item : currentItems) {
            boolean shouldPass = AskTheUser.decision(String.format("Atkerül-e a %s tárgy az új szobába?", Logger.getObjectName(item)));
            if (shouldPass) {
                Logger.selfInvokeMethod(this, "RemoveItem", List.of(item));
                RemoveItem(item);
                Logger.returnFromMethod(this, this, "RemoveItem", Optional.empty());
                Logger.invokeObjectMethod(this, r2, "AddItem", List.of(item));
                r2.AddItem(item);
                Logger.returnFromMethod(this, r2, "AddItem", Optional.empty());
            }
        }

        CopyOnWriteArrayList<RoomEffect> currentEffects = new CopyOnWriteArrayList<>(activeEffects);
        for (RoomEffect effect : currentEffects) {
            boolean shouldPass = AskTheUser.decision(String.format("Atkerül-e a %s effekt az új szobába?", Logger.getObjectName(effect)));
            if (shouldPass) {
                Logger.selfInvokeMethod(this, "RemoveEffect", List.of(effect));
                RemoveEffect(effect);
                Logger.returnFromMethod(this, this, "RemoveEffect", Optional.empty());
                Logger.invokeObjectMethod(this, r2, "AddEffect", List.of(effect));
                r2.AddEffect(effect);
                Logger.returnFromMethod(this, r2, "AddEffect", Optional.empty());
            }
        }

        for (Door door : doors) {
            Logger.invokeObjectMethod(this, door, "Show", List.of());
            door.Show();
            Logger.returnFromMethod(this, door, "Show", Optional.empty());
        }

        CopyOnWriteArrayList<Door> currentDoors = new CopyOnWriteArrayList<>(doors);
        for (Door door : currentDoors) {
            boolean shouldPass = AskTheUser.decision(String.format("Atkerül-e a %s ajtó az új szobába?", Logger.getObjectName(door)));
            if (shouldPass) {
                Logger.invokeObjectMethod(this, door, "ChangeRoom", List.of(this, r2));
                door.ChangeRoom(this, r2);
                Logger.returnFromMethod(this, door, "ChangeRoom", Optional.empty());
            }
        }

        var d3 = new Door();
        Logger.createObject(this, d3, "d3");

        Logger.invokeObjectMethod(this, d3, "SetRooms", List.of(this, r2));
        d3.SetRooms(this, r2);
        Logger.returnFromMethod(this, d3, "SetRooms", Optional.empty());
    }

    /**
     * Adds an effect to the room.
     * @param e The effect to be added.
     */
    public void AddEffect(RoomEffect e) {
        Logger.invokeObjectMethod(this, e, "SetLocation", Collections.singletonList(this));
        e.SetLocation(this);
        Logger.returnFromMethod(this, e, "SetLocation", Optional.empty());

        activeEffects.add(e);
    }

    /**
     * Removes an effect from the room.
     * @param e The effect to be removed.
     */
    public void RemoveEffect(RoomEffect e) {
        Logger.invokeObjectMethod(this, e, "SetLocation", Collections.singletonList(null));
        e.SetLocation(null);
        Logger.returnFromMethod(this, e, "SetLocation", Optional.empty());

        activeEffects.remove(e);
    }

    /**
     * Visits actors in the room using the provided visitor.
     * @param v The visitor object used to visit actors.
     */
    public void VisitActors(ActorVisitor v) {
        actorsInside.forEach(actor -> {
            Logger.invokeObjectMethod(this, actor, "VisitActor", List.of(v));
            actor.VisitActor(v);
            Logger.returnFromMethod(this, actor, "VisitActor", Optional.empty());
        });
    }

    /**
     * Adds a door to the room.
     * @param d The door to be added.
     */
    public void AddDoor(Door d) {
        doors.add(d);
    }

    /**
     * Removes a door from the room.
     * @param d The door to be removed.
     */
    public void RemoveDoor(Door d) {
        doors.remove(d);
    }

    /**
     * Retrieves the actors currently inside the room.
     * @return A list of actors in the room.
     */
    public List<Actor> GetActors() {
        return actorsInside;
    }

    /**
     * Retrieves the capacity of the room.
     * @return The capacity of the room.
     */
    public int GetCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the room.
     * @param i The new capacity of the room.
     */
    public void SetCapacity(int i) {
        capacity = i;
    }

    /**
     * Performs actions associated with the passage of time for the room.
     * This method checks if the room should split and updates active effects.
     */
    @Override
    public void TimePassed() {
        boolean shouldMerge = AskTheUser.decision("Akar a szoba egyesülni?");
        if (shouldMerge) {
            Logger.selfInvokeMethod(this, "Merge", Collections.emptyList());
            Merge();
            Logger.returnFromMethod(this, this, "Merge", Optional.empty());
        }

        boolean shouldSplit = AskTheUser.decision("Akar a szoba szétválni?");
        if (shouldSplit) {
            Logger.selfInvokeMethod(this, "Split", Collections.emptyList());
            Split();
            Logger.returnFromMethod(this, this, "Split", Optional.empty());
        }

        for (RoomEffect effect : activeEffects) {
            Logger.invokeObjectMethod(this, effect, "TimePassed", Collections.emptyList());
            effect.TimePassed();
            Logger.returnFromMethod(this, effect, "TimePassed", Optional.empty());
        }
    }
}
