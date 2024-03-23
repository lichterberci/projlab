package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Item implements Entity {
    protected boolean activated;
    protected boolean dead;
    protected Actor actor;
    public boolean PickUp(Actor a) {
        actor = a;
        return true;
    }
    
    public void Drop() {
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, actor, "DropItem", List.of(this));
        actor.DropItem(this);
        IndentedDebugPrinter.getInstance().returnFromMethod(this, actor, "DropItem", Optional.empty());
    }
    
    public boolean IsPickedUp() {
        return actor != null;
    }
    
    public void Activate() {
        var gp = new GasPoisoning();
        IndentedDebugPrinter.getInstance().createObject(this, gp, "gp");
        Room room = actor.GetLocation();
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, room, "AddEffect", List.of(gp));
        room.AddEffect(gp);
        IndentedDebugPrinter.getInstance().returnFromMethod(this, room, "AddEffect", Optional.empty());
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, this, "Drop", Collections.emptyList());
        Drop();
        IndentedDebugPrinter.getInstance().returnFromMethod(this, this, "Drop", Optional.empty());
    }
    
    public boolean IsActivated() {
        return activated;
    }
    
    public void ApplyCharges() {
    }
}
