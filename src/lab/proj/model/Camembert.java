package lab.proj.model;

import lab.proj.controller.GameManager;

import java.util.List;

/**
 * A class representing a Camembert item in the game environment.
 * Camembert's items extend the functionality of basic items.
 */
public class Camembert extends Item {

    public Camembert() {
        Logger.createObject(this);
    }

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
}
