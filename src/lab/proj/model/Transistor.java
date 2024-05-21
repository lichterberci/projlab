package lab.proj.model;

import lab.proj.controller.GameManager;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a transistor item in the game environment.
 * Transistors can be paired with other transistors and are subject to the passage of time.
 */
public class Transistor extends Item {

    /**
     * The paired transistor.
     */
    private Transistor pair = null;

    /**
     * Creates a new transistor item.
     */
    public Transistor() {
        Logger.createObject(this);
    }

    /**
     * Pairs the transistor with another transistor.
     *
     * @param tr The transistor to pair with.
     */
    public void PairWith(Transistor tr) {
        Logger.invokeMethod(this, Collections.singletonList(tr));

        if (fake || pair != null) {
            Logger.returnVoid();
            return;
        }

        tr.pair = this;
        this.pair = tr;

        Logger.returnVoid();
    }

    /**
     * Drops the transistor.
     * If the transistor is paired with another
     * transistor, both transistors are dropped.
     */
    @Override
    public void Drop() {
        Logger.invokeMethod(this, List.of());

        if (pair == null) {
            super.Drop();
        }

        Logger.returnVoid();
    }

    /**
     * Activates the transistor.
     * If the transistor is paired with another
     * transistor, both transistors are activated.
     */
    @Override
    public void Activate() {
        Logger.invokeMethod(this, List.of());

        if (fake) {
            Logger.returnVoid();
            return;
        }

        if (!activated) {
            actor.DropItem(this); // cannot call Drop() directly because it would set its actor to null
            activated = true;
        }

        if (location != actor.location && pair.location != actor.location || location == pair.location) {
            Logger.returnVoid();
            return;
        }

        if (location != actor.location && pair.activated) {
            Room prevLoc = actor.location;
            boolean success = location.StepIn(actor);
            if (success) {
                prevLoc.StepOut(actor);
            }
        } else if (pair.activated) {
            pair.Activate();
        }

        Logger.returnVoid();
    }

    /**
     * Attempts to pick up the transistor with the specified actor.
     * If the transistor is activated, the transistor is activated instead.
     *
     * @param a The actor attempting to pick up the transistor.
     * @return true if the transistor is successfully picked up, false otherwise.
     */
    @Override
    public boolean PickUp(Actor a) {
        if (activated) {
            Activate();
            return false;
        } else {
            return super.PickUp(a);
        }
    }

    /**
     * Visits the transistor item.
     *
     * @param iv The visitor to visit the item.
     */
    @Override
    public void VisitItem(ItemVisitor iv) {
        Logger.invokeMethod(this, Collections.singletonList(iv));

        iv.VisitTransistor(this);

        Logger.returnVoid();
    }
}
