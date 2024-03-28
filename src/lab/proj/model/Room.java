package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A class representing a room in the game environment.
 * Rooms can contain actors, items, and room effects.
 */
public class Room implements Entity {

    /**
     * A logger for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * The actors currently inside the room.
     */
    private final List<Actor> actorsInside = new ArrayList<>();

    /**
     * The items on the floor of the room.
     */
    private final List<Item> itemsOnTheFloor = new ArrayList<>();

    /**
     * The active effects present in the room.
     */
    private final List<RoomEffect> activeEffects = new ArrayList<>();

    /**
     * The doors connected to the room.
     */
    private final List<Door> doors = new ArrayList<>();

    /**
     * The capacity of the room.
     */
    private int capacity;

    /**
     * Adds an actor to the room.
     *
     * @param actor The actor to be added.
     */
    public void AddActor(Actor actor) {
        Logger.invokeMethod(this, Collections.singletonList(actor));

        actorsInside.add(actor);

        Logger.returnVoid();
    }

    /**
     * Removes an actor from the room.
     *
     * @param actor The actor to be removed.
     */
    public void RemoveActor(Actor actor) {
        Logger.invokeMethod(this, Collections.singletonList(actor));

        actorsInside.remove(actor);

        Logger.returnVoid();
    }

    /**
     * Checks if the room is at full capacity.
     *
     * @return true if the room is full, false otherwise.
     */
    public boolean IsFull() {
        Logger.invokeMethod(this, List.of());

        boolean roomIsFull = AskTheUser.decision("Tele van a szoba?");

        Logger.returnValue(roomIsFull);
        return roomIsFull;
    }

    /**
     * Allows an actor to step into the room.
     *
     * @param a The actor attempting to step into the room.
     * @return true if the actor successfully steps into the room, false otherwise.
     */
    public boolean StepIn(Actor a) {
        Logger.invokeMethod(this, Collections.singletonList(a));

        boolean isFull = IsFull();
        if (!isFull)
            a.SetLocation(this);

        Logger.returnValue(isFull);
        return true;
    }

    /**
     * Allows an actor to step out of the room.
     *
     * @param a The actor attempting to step out of the room.
     */
    public void StepOut(Actor a) {
        Logger.invokeMethod(this, Collections.singletonList(a));

        actorsInside.remove(a);

        Logger.returnVoid();
    }

    /**
     * Retrieves the doors connected to the room.
     *
     * @return A list of doors connected to the room.
     */
    public List<Door> GetDoors() {
        Logger.invokeMethod(this, List.of());
        Logger.returnValue(doors);
        return doors;
    }

    /**
     * Retrieves the items on the floor of the room.
     *
     * @return A list of items on the floor of the room.
     */
    public List<Item> GetItems() {
        Logger.invokeMethod(this, List.of());
        Logger.returnValue(itemsOnTheFloor);
        return itemsOnTheFloor;
    }

    /**
     * Adds an item to the floor of the room.
     *
     * @param i The item to be added.
     */
    public void AddItem(Item i) {
        Logger.invokeMethod(this, Collections.singletonList(i));

        i.SetLocation(this);
        itemsOnTheFloor.add(i);

        Logger.returnVoid();
    }

    /**
     * Removes an item from the floor of the room.
     *
     * @param i The item to be removed.
     */
    public void RemoveItem(Item i) {
        Logger.invokeMethod(this, Collections.singletonList(i));

        i.SetLocation(null);
        itemsOnTheFloor.remove(i);

        Logger.returnVoid();
    }

    /**
     * Merges the room with another room.
     */
    public void Merge() {
        Logger.invokeMethod(this, List.of());

        if (!actorsInside.isEmpty())
            return;

        Room r2 = null;
        for (Door door : doors) {
            List<Room> doorRooms = door.GetRooms();
            Room otherRoom = (doorRooms.get(0) == this) ? doorRooms.get(doorRooms.size() - 1) : doorRooms.get(0);
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

            r2.RemoveItem(item);

            AddItem(item);

        }

        capacity = r2.capacity;
        // fake it till you make it
        for (Door door : doors) {

            door.Show();

        }

        Logger.destroyObject(r2);

        Logger.returnVoid();
    }

    /**
     * Splits the room into two separate rooms.
     */
    public void Split() {
        Logger.invokeMethod(this, List.of());

        var r2 = new Room();
        Logger.createObject(r2, "r2");

        CopyOnWriteArrayList<Item> currentItems = new CopyOnWriteArrayList<>(itemsOnTheFloor);
        for (Item item : currentItems) {
            boolean shouldPass = AskTheUser.decision(String.format("Atkerül-e a %s tárgy az új szobába?", Logger.getObjectName(item)));
            if (shouldPass) {
                RemoveItem(item);
                r2.AddItem(item);
            }
        }

        CopyOnWriteArrayList<RoomEffect> currentEffects = new CopyOnWriteArrayList<>(activeEffects);
        for (RoomEffect effect : currentEffects) {
            boolean shouldPass = AskTheUser.decision(String.format("Atkerül-e a %s effekt az új szobába?", Logger.getObjectName(effect)));
            if (shouldPass) {
                RemoveEffect(effect);
                r2.AddEffect(effect);
            }
        }

        for (Door door : doors)
            door.Show();

        CopyOnWriteArrayList<Door> currentDoors = new CopyOnWriteArrayList<>(doors);
        for (Door door : currentDoors) {
            boolean shouldPass = AskTheUser.decision(String.format("Atkerül-e a %s ajtó az új szobába?", Logger.getObjectName(door)));
            if (shouldPass)
                door.ChangeRoom(this, r2);
        }

        var d3 = new Door();
        Logger.createObject(d3, "d3");
        d3.SetRooms(this, r2);

        Logger.returnVoid();
    }

    /**
     * Adds an effect to the room.
     *
     * @param e The effect to be added.
     */
    public void AddEffect(RoomEffect e) {
        Logger.invokeMethod(this, Collections.singletonList(e));
        e.SetLocation(this);
        activeEffects.add(e);

        Logger.returnVoid();
    }

    /**
     * Removes an effect from the room.
     *
     * @param e The effect to be removed.
     */
    public void RemoveEffect(RoomEffect e) {
        Logger.invokeMethod(this, Collections.singletonList(e));

        e.SetLocation(null);
        activeEffects.remove(e);

        Logger.returnVoid();
    }

    /**
     * Visits actors in the room using the provided visitor.
     *
     * @param v The visitor object used to visit actors.
     */
    public void VisitActors(ActorVisitor v) {
        Logger.invokeMethod(this, Collections.singletonList(v));

        actorsInside.forEach(actor -> actor.VisitActor(v));

        Logger.returnVoid();
    }

    /**
     * Adds a door to the room.
     *
     * @param d The door to be added.
     */
    public void AddDoor(Door d) {
        Logger.invokeMethod(this, Collections.singletonList(d));

        doors.add(d);

        Logger.returnVoid();
    }

    /**
     * Removes a door from the room.
     *
     * @param d The door to be removed.
     */
    public void RemoveDoor(Door d) {
        Logger.invokeMethod(this, Collections.singletonList(d));

        doors.remove(d);

        Logger.returnVoid();
    }

    /**
     * Retrieves the actors currently inside the room.
     *
     * @return A list of actors in the room.
     */
    public List<Actor> GetActors() {
        Logger.invokeMethod(this, List.of());
        Logger.returnValue(actorsInside);
        return actorsInside;
    }

    /**
     * Retrieves the capacity of the room.
     *
     * @return The capacity of the room.
     */
    public int GetCapacity() {
        Logger.invokeMethod(this, List.of());
        Logger.returnValue(capacity);
        return capacity;
    }

    /**
     * Sets the capacity of the room.
     *
     * @param i The new capacity of the room.
     */
    public void SetCapacity(int i) {
        Logger.invokeMethod(this, Collections.singletonList(i));

        capacity = i;

        Logger.returnVoid();
    }

    /**
     * Performs actions associated with the passage of time for the room.
     * This method checks if the room should split and updates active effects.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        boolean shouldMerge = AskTheUser.decision("Akar a szoba egyesülni?");
        if (shouldMerge)
            Merge();

        boolean shouldSplit = AskTheUser.decision("Akar a szoba szétválni?");
        if (shouldSplit)
            Split();

        for (RoomEffect effect : activeEffects)
            effect.TimePassed();

        Logger.returnVoid();
    }
}
