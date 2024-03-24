package lab.proj.model;


import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class Actor implements Entity {
    protected boolean incapacitated;
    private Room location;
    private final List<Item> collectedItems = new ArrayList<>();

    public boolean UseDoor(Door d) {
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, d, "GoThrough", List.of());
        Room dst = Arrays.stream(d.GetRooms())
                .filter(r -> r != location)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Door connects to the same room on both sides!"));
        boolean wasSuccessful = d.GoThrough(dst, this);
        if (wasSuccessful) {
            IndentedDebugPrinter.getInstance().invokeObjectMethod(this, location, "StepOut", List.of(this));
            location.StepOut(this);
            IndentedDebugPrinter.getInstance().returnFromMethod(this, location, "StepOut", Optional.empty());
        }
        IndentedDebugPrinter.getInstance().returnFromMethod(this, d, "GoThrough", Optional.of(wasSuccessful));
        return wasSuccessful;
    }

    public void CollectItem(Item i) {
        collectedItems.add(i);
    }

    public void DropItem(Item i) {
        // this would cause an infinite recursion :((
//        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, i, "Drop", Collections.emptyList());
//        i.Drop();
//        IndentedDebugPrinter.getInstance().returnFromMethod(this, i, "Drop", Optional.empty());
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, location, "AddItem", List.of(i));
        location.AddItem(i);
        IndentedDebugPrinter.getInstance().returnFromMethod(this, location, "AddItem", Optional.empty());
    }

    public List<Item> GetItems() {
        return collectedItems;
    }

    public Room GetLocation() {
        return location;
    }

    public void SetLocation(Room r) {
        location = r;
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, r, "AddActor", List.of(this));
        r.AddActor(this);
        IndentedDebugPrinter.getInstance().returnFromMethod(this, r, "AddActor", Optional.empty());
    }

    public abstract void VisitActor(ActorVisitor v);

    public abstract void Shock();

    public boolean IsBlocked() {
        return incapacitated;
    }
}
