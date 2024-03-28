package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a student actor in the game environment.
 * Students can interact with items, be affected by various effects, and potentially drop out.
 */
public class Student extends Actor {

    /**
     * A logger for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * Indicates whether the student has dropped out.
     */
    private boolean droppedOut;

    public Student() {
        Logger.createObject(this);
    }

    /**
     * Removes a charge from the student.
     * This method is currently empty.
     *
     * @param c The charge to be removed.
     */
    public void RemoveCharge(Charge c) {
        Logger.invokeMethod(this, Collections.singletonList(c));

        // Empty method

        Logger.returnVoid();
    }

    /**
     * Checks if the student has dropped out.
     *
     * @return true if the student has dropped out, false otherwise.
     */
    public boolean IsDroppedOut() {
        Logger.invokeMethod(this, List.of());
        Logger.returnValue(droppedOut);
        return droppedOut;
    }

    /**
     * Performs actions associated with the passage of time for the student.
     * This method clears any gas protections and dropout protections the student has, and applies charges to collected items.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        gasProtections.clear();
        dropOutProtections.clear();
        for (Item i : collectedItems)
            i.ApplyCharges();

        Logger.returnVoid();
    }

    /**
     * Accepts a visitor, allowing it to visit the student.
     *
     * @param v The visitor to be accepted.
     */
    @Override
    public void VisitActor(ActorVisitor v) {
        Logger.invokeMethod(this, Collections.singletonList(v));

        v.VisitStudent(this);

        Logger.returnVoid();
    }

    /**
     * Reacts to being shocked, potentially applying gas protection.
     */
    @Override
    public void Shock() {
        Logger.invokeMethod(this, List.of());

        if (AskTheUser.decision("Does the player have a mask?")) {
            var gasProtection = gasProtections.stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Student does not have a mask"));

            gasProtection.Affect();
        }

        Logger.returnVoid();
    }

    /**
     * Initiates the process of dropping out, potentially applying dropout protection.
     */
    @Override
    public void DropOut() {
        Logger.invokeMethod(this, List.of());

        if (dropOutProtections.isEmpty()) {
            droppedOut = true;
        } else {
            DropOutProtection dropOutProtection = dropOutProtections.get(0);

            dropOutProtection.Affect();

            dropOutProtections.remove(0);
        }

        Logger.returnVoid();
    }
}
