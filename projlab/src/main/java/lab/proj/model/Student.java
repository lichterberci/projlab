package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.Optional;

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

    /**
     * Removes a charge from the student.
     * This method is currently empty.
     *
     * @param c The charge to be removed.
     */
    public void RemoveCharge(Charge c) {
        // Empty method
    }

    /**
     * Checks if the student has dropped out.
     *
     * @return true if the student has dropped out, false otherwise.
     */
    public boolean IsDroppedOut() {
        return droppedOut;
    }

    /**
     * Performs actions associated with the passage of time for the student.
     * This method clears any gas protections and dropout protections the student has, and applies charges to collected items.
     */
    @Override
    public void TimePassed() {
        gasProtections.clear();
        dropOutProtections.clear();
        for (Item i : collectedItems) {
            Logger.invokeObjectMethod(this, i, "ApplyCharges", Collections.emptyList());
            i.ApplyCharges();
            Logger.returnFromMethod(this, i, "ApplyCharges", Optional.empty());
        }
    }

    /**
     * Accepts a visitor, allowing it to visit the student.
     *
     * @param v The visitor to be accepted.
     */
    @Override
    public void VisitActor(ActorVisitor v) {
        v.VisitStudent(this);
    }

    /**
     * Reacts to being shocked, potentially applying gas protection.
     */
    @Override
    public void Shock() {
        if (AskTheUser.decision("Does the player have a mask?")) {
            var gasProtection = gasProtections.stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Student does not have a mask"));
            Logger.invokeObjectMethod(this, gasProtection, "Affect", Collections.emptyList());
            gasProtection.Affect();
            Logger.returnFromMethod(this, gasProtection, "Affect", Optional.empty());
        }
    }

    /**
     * Initiates the process of dropping out, potentially applying dropout protection.
     */
    @Override
    public void DropOut() {
        if (dropOutProtections.isEmpty()) {
            droppedOut = true;
        } else {
            DropOutProtection dropOutProtection = dropOutProtections.getFirst();
            Logger.invokeObjectMethod(this, dropOutProtection, "Affect", Collections.emptyList());
            dropOutProtection.Affect();
            Logger.returnFromMethod(this, dropOutProtection, "Affect", Optional.empty());
            dropOutProtections.removeFirst();
        }
    }
}
