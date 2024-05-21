package lab.proj.model;

import lab.proj.controller.GameManager;

import java.util.List;

/**
 * A class representing a Camembert item in the game environment.
 * Camembert's items extend the functionality of basic items.
 */
public class Camembert extends Item {

    /**
     * Creates a new Camembert.
     */
    public Camembert() {
        Logger.createObject(this);
    }

    /**
     * Activates the Camembert.
     * This method creates a gas poisoning in the room where the Camembert is located.
     */
    @Override
    public void Activate() {
        Logger.invokeMethod(this, List.of());

        if (fake) {
            Logger.returnVoid();
            return;
        }
        activated = true;

        var gp = new GasPoisoning();

        Drop();
        gp.SetLocation(location);
        dead = true;
        Logger.returnVoid();
    }

    /**
     * Visits the Camembert item.
     *
     * @param iv The visitor to visit the item.
     */
    @Override
    public void VisitItem(ItemVisitor iv) {
        iv.VisitCamembert(this);
    }
}
