package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;

/**
 * A class representing a transistor item in the game environment.
 * Transistors can be paired with other transistors and are subject to the passage of time.
 */
public class Transistor extends Item {

    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * The paired transistor.
     */
    private Transistor pair = null;

    /**
     * Drops the transistor.
     * This method is currently empty.
     */
    public void Drop() {
        Logger.invokeMethod(this, List.of());

        // Empty method
    }

    /**
     * Pairs the transistor with another transistor.
     * This method is currently empty.
     *
     * @param t The transistor to pair with.
     */
    public void PairWith(Transistor t) {
        Logger.invokeMethod(this, List.of(t));

        boolean result = AskTheUser.decision("Párosított már a tranzisztor?");

        if (result) {
        } else {

            t.SetPair(this);

            this.SetPair(t);

        }
    }

    /**
     * Performs actions associated with the passage of time for the transistor.
     * This method is currently empty.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        // Empty method
    }

    public void SetPair(Transistor t) {
        Logger.invokeMethod(this, List.of(t));

        this.pair = t;
    }

    @Override
    public void Activate() {
        Logger.invokeMethod(this, List.of());

        if (this.activated) {
            if (this.location != this.actor.location && this.pair != null && this.pair.activated) {
                Room prevLoc = this.actor.location;
                boolean success = this.location.StepIn(this.actor);

                if (success) {

                    prevLoc.StepOut(this.actor);

                }
            } else {
                if (this.pair != null && this.pair.activated) {

                    this.pair.Activate();

                }
            }
        } else if (this.pair != null) {
            this.activated = true;

            this.actor.DropItem(this);

        }
    }
}
