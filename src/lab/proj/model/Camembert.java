package lab.proj.model;

import java.util.List;

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
        Logger.invokeMethod(this, List.of());        // No actions for Camembert on time passage

        Logger.returnVoid();
    }

    @Override
    public void Activate() {
        Logger.invokeMethod(this, List.of());

        var gp = new GasPoisoning();
        Logger.createObject(gp, "gp");
        Room room = actor.GetLocation();
        room.AddEffect(gp);
        Drop();

        Logger.returnVoid();
    }
}
