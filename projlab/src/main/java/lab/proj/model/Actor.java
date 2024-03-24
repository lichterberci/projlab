package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class Actor implements Entity {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    protected boolean incapacitated;
    protected Room location;
    private final List<Item> collectedItems = new ArrayList<>();

    public boolean UseDoor(Door d) {
        Logger.invokeObjectMethod(this, d, "GoThrough", List.of(location, this));
        boolean wasSuccessful = d.GoThrough(location, this);
        Logger.returnFromMethod(this, d, "GoThrough", Optional.of(wasSuccessful));

        if (wasSuccessful) {
            Logger.invokeObjectMethod(this, location, "StepOut", List.of(this));
            location.StepOut(this);
            Logger.returnFromMethod(this, location, "StepOut", Optional.empty());
        }

        return wasSuccessful;
    }

    public void CollectItem(Item i) {
        Logger.invokeObjectMethod(this, location, "RemoveItem", List.of(i));
        location.RemoveItem(i);
        Logger.returnFromMethod(this, location, "RemoveItem", Optional.empty());

        collectedItems.add(i);
    }

    public void DropItem(Item i) {
        // this would cause an infinite recursion :((
//        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, i, "Drop", Collections.emptyList());
//        i.Drop();
//        IndentedDebugPrinter.getInstance().returnFromMethod(this, i, "Drop", Optional.empty());
        Logger.invokeObjectMethod(this, location, "AddItem", List.of(i));
        location.AddItem(i);
        Logger.returnFromMethod(this, location, "AddItem", Optional.empty());
    }

    public List<Item> GetItems() {
        return collectedItems;
    }

    public Room GetLocation() {
        return location;
    }

    public void SetLocation(Room r) {
        location = r;
        Logger.invokeObjectMethod(this, r, "AddActor", List.of(this));
        r.AddActor(this);
        Logger.returnFromMethod(this, r, "AddActor", Optional.empty());
    }

    public abstract void VisitActor(ActorVisitor v);

    public abstract void Shock();

    public boolean IsBlocked() {
        return incapacitated;
    }

    public void DropOut() {
        // Empty by default..
    }
}
