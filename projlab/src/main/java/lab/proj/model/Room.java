package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Room implements Entity {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    private int capacity;
    private final List<Actor> actorsInside = new ArrayList<>();
    private final List<Item> itemsOnTheFloor = new ArrayList<>();
    private final List<RoomEffect> activeEffects = new ArrayList<>();

    public boolean StepIn(Actor a) {
        boolean roomIsFull = AskTheUser.decision("Tele van a szoba?");

        if (roomIsFull)
            return false;

        actorsInside.add(a);

        return true;
    }

    public void StepOut(Actor a) {
        actorsInside.remove(a);
    }

    public List<Door> GetDoors() {
        throw new RuntimeException();
    }

    public List<Item> GetItems() {
        return itemsOnTheFloor;
    }

    public void AddItem(Item i) {
        Logger.invokeObjectMethod(this, i, "SetLocation", List.of(this));
        i.SetLocation(this);
        Logger.returnFromMethod(this, i, "SetLocation", Optional.empty());

        itemsOnTheFloor.add(i);
    }

    public void RemoveItem(Item i) {
        Logger.invokeObjectMethod(this, i, "SetLocation", List.of());
        i.SetLocation(null);
        Logger.returnFromMethod(this, i, "SetLocation", Optional.empty());

        itemsOnTheFloor.remove(i);
    }

    public void Merge() {
    }

    public void Split() {
    }

    public void AddEffect(RoomEffect e) {
        activeEffects.add(e);
    }

    public void RemoveEffect(RoomEffect e) {
        activeEffects.remove(e);
    }

    public void VisitActors(ActorVisitor v) {
        actorsInside.forEach(actor -> {
            Logger.invokeObjectMethod(this, actor, "VisitActor", List.of(v));
            actor.VisitActor(v);
            Logger.returnFromMethod(this, actor, "VisitActor", Optional.empty());
        });
    }

    public void AddDoor(Door d) {
    }

    public void RemoveDoor(Door d) {
    }

    public List<Actor> GetActors() {
        return actorsInside;
    }

    public int GetCapacity() {
        return capacity;
    }

    public void SetCapacity(int i) {
        capacity = i;
    }

    public List<RoomEffect> GetEffects() {
        return activeEffects;
    }

    @Override
    public void TimePassed() {
        activeEffects.forEach(Entity::TimePassed);
    }

    public void AddActor(Actor actor) {
        actorsInside.add(actor);
    }
}
