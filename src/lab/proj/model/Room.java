package lab.proj.model;

import lab.proj.controller.GameManager;
import lab.proj.utils.Randomware;
import lab.proj.utils.SequenceDiagramPrinter;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A class representing a room in the game environment.
 * Rooms can contain actors, items, and room effects.
 */
public class Room implements Entity {

    public static final float MERGE_LIKELIHOOD = 0.2f;
    public static final float SPLIT_LIKELIHOOD = 0.1f;
    /**
     * A logger for debugging purposes.
     */
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();
    private static final int STICKY_THRESHOLD = 5;
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

    private int visitorCountSinceLastCleaning = 0;

    public Room() {
        Logger.createObject(this);
    }

    /**
     * Adds an actor to the room.
     *
     * @param actor The actor to be added.
     */
    public void AddActor(Actor actor) {
        Logger.invokeMethod(this, Collections.singletonList(actor));

        actor.SetLocation(this);
        actorsInside.add(actor);
        visitorCountSinceLastCleaning++;

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

    public boolean IsEmpty() {
        Logger.invokeMethod(this, List.of());

        boolean roomIsEmpty = actorsInside.isEmpty();

        Logger.returnValue(roomIsEmpty);
        return roomIsEmpty;
    }

    /**
     * Checks if the room is at full capacity.
     *
     * @return true if the room is full, false otherwise.
     */
    public boolean IsFull() {
        Logger.invokeMethod(this, List.of());

        boolean roomIsFull = actorsInside.size() >= capacity;

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
            AddActor(a);

        Logger.returnValue(!isFull);
        return !isFull;
    }

    /**
     * Allows an actor to step out of the room.
     *
     * @param a The actor attempting to step out of the room.
     */
    public void StepOut(Actor a) {
        Logger.invokeMethod(this, Collections.singletonList(a));

        RemoveActor(a);

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

    private void Merge() {
        Logger.invokeMethod(this, List.of());

        Room r2 = null;

        for (Door door : doors) {
            List<Room> doorRooms = door.GetRooms();
            Room otherRoom = (doorRooms.get(0) == this) ? doorRooms.get(doorRooms.size() - 1) : doorRooms.get(0);
            boolean chooseThis = IsFull() && !otherRoom.IsFull();
            if (chooseThis) {
                r2 = otherRoom;
                break;
            }
        }

        if (r2 != null)
            Merge(r2);

        Logger.returnVoid();
    }

    /**
     * Merges the room with another room.
     *
     * @param r2
     */
    public void Merge(Room r2) {
        Logger.invokeMethod(this, List.of());

        if (!IsEmpty() || !r2.IsEmpty()) {
            Logger.returnVoid();
            return;
        }

        CopyOnWriteArrayList<Item> otherRoomsItems = new CopyOnWriteArrayList<>(r2.itemsOnTheFloor);
        for (Item item : otherRoomsItems) {
            r2.RemoveItem(item);
            AddItem(item);
        }

        capacity = r2.capacity;

        for (Door door : doors)
            door.Show();

        Logger.destroyObject(r2); // in reality, it does not die immediately

        Logger.returnVoid();
    }

    /**
     * Splits the room into two separate rooms.
     */
    public void Split(Set<Item> itemsToPass, Set<RoomEffect> effectsToPass, Set<Door> doorsToPass) {
        Logger.invokeMethod(this, List.of());

//        var r2 = new Room();
        var r2 = GameManager.GetInstance().CreateRoom();

        CopyOnWriteArrayList<Item> currentItems = new CopyOnWriteArrayList<>(itemsOnTheFloor);
        for (Item item : currentItems) {
            boolean shouldPass = itemsToPass.contains(item);
            if (shouldPass) {
                RemoveItem(item);
                r2.AddItem(item);
            }
        }

        CopyOnWriteArrayList<RoomEffect> currentEffects = new CopyOnWriteArrayList<>(activeEffects);
        for (RoomEffect effect : currentEffects) {
            boolean shouldPass = effectsToPass.contains(effect);
            if (shouldPass) {
                RemoveEffect(effect);
                r2.AddEffect(effect);
            }
        }

        for (Door door : doors)
            door.Show();

        CopyOnWriteArrayList<Door> currentDoors = new CopyOnWriteArrayList<>(doors);
        for (Door door : currentDoors) {
            boolean shouldPass = doorsToPass.contains(door);
            if (shouldPass)
                door.ChangeRoom(this, r2);
        }

        var d3 = new Door();
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

    public void VisitEffects(RoomEffectVisitor rev) {
        Logger.invokeMethod(this, Collections.singletonList(rev));

        activeEffects.forEach(re -> re.VisitRoomEffect(rev));

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

        boolean shouldMerge = Randomware.Decision(MERGE_LIKELIHOOD);
        if (shouldMerge)
            Merge();

        boolean shouldSplit = Randomware.Decision(SPLIT_LIKELIHOOD);
        if (shouldSplit) {
            Set<Item> itemsToPass = new HashSet<>(Randomware.Subset(itemsOnTheFloor));
            Set<RoomEffect> effectsToPass = new HashSet<>(Randomware.Subset(activeEffects));
            Set<Door> doorsToPass = new HashSet<>(Randomware.Subset(doors));
            Split(itemsToPass, effectsToPass, doorsToPass);
        }

        for (RoomEffect effect : activeEffects)
            effect.TimePassed();

        RefreshStickyness();

        Logger.returnVoid();
    }

    public void CleanRoom() {
        visitorCountSinceLastCleaning = 0;
        RefreshStickyness();
    }

    private void RefreshStickyness() {
        for (Item item : itemsOnTheFloor)
            item.SetSticky(visitorCountSinceLastCleaning > STICKY_THRESHOLD);
    }
}
