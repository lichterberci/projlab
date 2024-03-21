package lab.proj.model;


import jdk.jshell.spi.ExecutionControl;

import java.util.List;

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
