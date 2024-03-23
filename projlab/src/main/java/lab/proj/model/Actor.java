package lab.proj.model;


import jdk.jshell.spi.ExecutionControl;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.*;

public abstract class Actor implements Entity {
    protected boolean incapacitated;
    private Room location;
    private List<Item> collectedItems = new ArrayList<>();
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
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, i, "Drop", Collections.emptyList());
        i.Drop();
        IndentedDebugPrinter.getInstance().returnFromMethod(this, i, "Drop", Optional.empty());
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
    public void VisitActor(ActorVisitor v) {
    }
    
    public void Shock() {
    }
    
    public boolean IsBlocked() {
        return incapacitated;
    }
}
