package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Item implements Entity {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    protected boolean activated;
    protected boolean dead;
    protected Room location;
    protected Actor actor;

    public boolean PickUp(Actor a) {
        Logger.invokeObjectMethod(this, a, "CollectItem", List.of(this));
        a.CollectItem(this);
        Logger.returnFromMethod(this, a, "CollectItem", Optional.empty());

        actor = a;
        return true;
    }

    public void Drop() {
        Logger.invokeObjectMethod(this, actor, "DropItem", List.of(this));
        actor.DropItem(this);
        Logger.returnFromMethod(this, actor, "DropItem", Optional.empty());
    }

    public void SetLocation(Room location) {
        this.location = location;
    }

    public boolean IsPickedUp() {
        return actor != null;
    }

    public void Activate() {
        var gp = new GasPoisoning();
        Logger.createObject(this, gp, "gp");
        
        Logger.invokeObjectMethod(this, actor, "GetLocation", List.of());
        Room room = actor.GetLocation();
        Logger.returnFromMethod(this, actor, "GetLocation", Optional.of(room));
        
        Logger.invokeObjectMethod(this, room, "AddEffect", List.of(gp));
        room.AddEffect(gp);
        Logger.returnFromMethod(this, room, "AddEffect", Optional.empty());
        
        Logger.invokeObjectMethod(this, this, "Drop", Collections.emptyList());
        Drop();
        Logger.returnFromMethod(this, this, "Drop", Optional.empty());
    }

    public boolean IsActivated() {
        return activated;
    }

    public void ApplyCharges() {
    }
}
