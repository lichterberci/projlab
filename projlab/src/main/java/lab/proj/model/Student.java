package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.List;
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
            Logger.invokeObjectMethod(i, "ApplyCharges", Collections.emptyList());
            i.ApplyCharges();
            Logger.returnFromMethod(i, "ApplyCharges", Optional.empty());
        }
    }

    /**
     * Accepts a visitor, allowing it to visit the student.
     *
     * @param v The visitor to be accepted.
     */
    @Override
    public void VisitActor(ActorVisitor v) {
        Logger.invokeObjectMethod(v, "VisitStudent", List.of(this));
        v.VisitStudent(this);
        Logger.returnFromMethod(v, "VisitStudent", Optional.empty());
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
            Logger.invokeObjectMethod(gasProtection, "Affect", Collections.emptyList());
            gasProtection.Affect();
            Logger.returnFromMethod(gasProtection, "Affect", Optional.empty());
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
            DropOutProtection dropOutProtection = dropOutProtections.get(0);
            Logger.invokeObjectMethod(dropOutProtection, "Affect", Collections.emptyList());
            dropOutProtection.Affect();
            Logger.returnFromMethod(dropOutProtection, "Affect", Optional.empty());
            dropOutProtections.remove(0);
        }
    }
}
