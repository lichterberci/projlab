package lab.proj.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A class representing a Camembert item in the game environment.
 * Camembert's items extend the functionality of basic items.
 */
public class Camembert extends Item {

    /**
     * Performs actions associated with the passage of time.
     * This method is currently empty for Camembert items.
     */
    @Override
    public void TimePassed() {
        // No actions for Camembert on time passage
    }

    @Override
    public void Activate() {
        super.Activate();
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
}
