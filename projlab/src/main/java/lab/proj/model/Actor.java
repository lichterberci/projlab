package lab.proj.model;


import jdk.jshell.spi.ExecutionControl;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Actor implements Entity {
    protected boolean incapacitated;
    private Room location;
    private List<Item> collectedItems;
    public boolean UseDoor(Door d) {
        throw new RuntimeException();
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
    
    public void VisitActor(ActorVisitor v) {
    }
    
    public void Shock() {
    }
    
    public boolean IsBlocked() {
        return incapacitated;
    }
}
