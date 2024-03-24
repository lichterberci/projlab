package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Actor implements Entity {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    protected boolean incapacitated;
    protected Room location;
    protected List<Item> collectedItems = new ArrayList<>();
    protected List<GasProtection> gasProtections = new ArrayList<>();
    protected List<DropOutProtection> dropOutProtections = new ArrayList<>();
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
        Logger.invokeObjectMethod(this, location, "AddItem", List.of(i));
        location.AddItem(i);
        IndentedDebugPrinter.getInstance().returnFromMethod(this, location, "AddItem", Optional.empty());
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, i, "SetLocation", List.of(location));
        i.SetLocation(location);
        IndentedDebugPrinter.getInstance().returnFromMethod(this, i, "SetLocation", Optional.empty());
    }

    public void AddDropOutProtection(DropOutProtection dropOutProtection) {
        dropOutProtections.add(dropOutProtection);
    }

    public void AddGasProtection(GasProtection gasProtection) {
        gasProtections.add(gasProtection);
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

    public abstract void DropOut();
}
