package lab.proj.model;

import lab.proj.utils.SequenceDiagramPrinter;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a transistor item in the game environment.
 * Transistors can be paired with other transistors and are subject to the passage of time.
 */
public class Transistor extends Item {

    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    /**
     * The paired transistor.
     */
    private Transistor pair = null;

    public Transistor() {
        Logger.createObject(this);
    }

    /**
     * Pairs the transistor with another transistor.
     * This method is currently empty.
     *
     * @param t The transistor to pair with.
     */
    public void PairWith(Transistor t) {
        Logger.invokeMethod(this, Collections.singletonList(t));

        if (fake) {
            Logger.returnVoid();
            return;
        }

        if (pair == null) {
            t.SetPair(this);
            this.SetPair(t);
        }

        Logger.returnVoid();
    }

    /**
     * Performs actions associated with the passage of time for the transistor.
     * This method is currently empty.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        // Empty method

        Logger.returnVoid();
    }

    public void SetPair(Transistor t) {
        Logger.invokeMethod(this, Collections.singletonList(t));

        if (fake) {
            Logger.returnVoid();
            return;
        }

        this.pair = t;

        Logger.returnVoid();
    }

    @Override
    public void Drop() {
        if (pair == null) {
            super.Drop();
        } else {
            Logger.invokeMethod(this, List.of());
            Logger.returnVoid();
        }
    }

    @Override
    public void Activate() {
        Logger.invokeMethod(this, List.of());

        if (fake) {
            Logger.returnVoid();
            return;
        }

        if (activated) {
            if (location != actor.location && pair != null && pair.activated) {
                Room prevLoc = actor.location;
                boolean success = location.StepIn(actor);
                if (success) {
                    prevLoc.StepOut(actor);
                }
            } else if (pair != null && pair.activated) {
                pair.Activate();
            }
        } else if (pair != null) {
            actor.DropItem(this); // cannot call Drop() directly because it would set its actor to null
            activated = true;
        }

        Logger.returnVoid();
    }
}
