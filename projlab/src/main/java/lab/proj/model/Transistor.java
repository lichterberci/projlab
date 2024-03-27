package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

/**
 * A class representing a transistor item in the game environment.
 * Transistors can be paired with other transistors and are subject to the passage of time.
 */
public class Transistor extends Item {

    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /** The paired transistor. */
    private Transistor pair = null;

    /**
     * Drops the transistor.
     * This method is currently empty.
     */
    public void Drop() {
        // Empty method
    }

    /**
     * Pairs the transistor with another transistor.
     * This method is currently empty.
     * @param t The transistor to pair with.
     */
    public void PairWith(Transistor t) {
        boolean result = AskTheUser.decision("Párosított már a tranzisztor?");

        if(result){
            return;
        }else{
            Logger.invokeObjectMethod(t, "SetPair", List.of(this));
            t.SetPair(this);
            Logger.returnFromMethod(
                    t,
                    "SetPair",
                    Optional.empty());

            Logger.invokeObjectMethod(this, "SetPair", List.of(t));
            this.SetPair(t);
            Logger.returnFromMethod(
                    this,
                    "SetPair",
                    Optional.empty());
        }
    }

    /**
     * Performs actions associated with the passage of time for the transistor.
     * This method is currently empty.
     */
    @Override
    public void TimePassed() {
        // Empty method
    }

    public void SetPair(Transistor t){
        this.pair = t;
    }

    @Override
    public void Activate(){
        if(this.activated){
            if(this.location != this.actor.location && this.pair != null && this.pair.activated){
                Room prevLoc = this.actor.location;

                Logger.invokeObjectMethod(this.location, "StepIn", List.of(this.actor));
                boolean success = this.location.StepIn(this.actor);
                Logger.returnFromMethod(
                        this.location,
                        "StepIn",
                        Optional.of(success));
                if(success){
                    Logger.invokeObjectMethod(prevLoc, "StepOut", List.of(this.actor));
                    prevLoc.StepOut(this.actor);
                    Logger.returnFromMethod(
                            prevLoc,
                            "StepOut",
                            Optional.empty());
                }
            }else{
                if(this.pair!=null && this.pair.activated){
                    Logger.invokeObjectMethod(this.pair, "Activate", List.of());
                    this.pair.Activate();
                    Logger.returnFromMethod(
                            this.pair,
                            "Activate",
                            Optional.empty());
                }
            }
        }else if(this.pair != null){
            this.activated = true;
            Logger.invokeObjectMethod(this.actor, "DropItem", List.of(this));
            this.actor.DropItem(this);
            Logger.returnFromMethod(
                    this.actor,
                    "DropItem",
                    Optional.empty());
        }
    }
}
