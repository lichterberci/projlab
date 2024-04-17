package lab.proj.model;

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

    @Override
    public void Drop() {
        Logger.invokeMethod(this, List.of());

        if (pair == null) {
            super.Drop();
        }

        Logger.returnVoid();
    }

    @Override
    public void Activate() {
        Logger.invokeMethod(this, List.of());

        if (fake) {
            Logger.returnVoid();
            return;
        }

        if (activated) {
            if (location != actor.location && pair.location != actor.location) {
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
        } else if (pair != null) {
            actor.DropItem(this); // cannot call Drop() directly because it would set its actor to null
            activated = true;
        }

        Logger.returnVoid();
    }
}
