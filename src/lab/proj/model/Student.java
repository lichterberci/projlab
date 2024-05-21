package lab.proj.model;

import lab.proj.controller.GameManager;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A class representing a student actor in the game environment.
 * Students can interact with items, be affected by various effects, and potentially drop out.
 */
public class Student extends Actor {

    /**
     * Indicates whether the student has dropped out.
     */
    private boolean droppedOut;

    /**
     * Creates a new student with the default name "Student".
     */
    public Student() {
        this.name = "Student";
        Logger.createObject(this);
    }

    /**
     * Creates a new student with the specified name.
     *
     * @param name The name of the student.
     */
    public Student(String name) {
        this.name = name;
        Logger.createObject(this);
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

        for (Item i : new CopyOnWriteArrayList<>(collectedItems)) {
            i.ApplyCharges();
            i.TimePassed();
        }

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

        if (gasProtections.isEmpty()) {
            super.Shock();
            dropOutProtections.clear();
            gasProtections.clear();
        } else {
            var gasProtection = gasProtections.poll();
            gasProtection.Affect();
        }

        Logger.returnVoid();
    }

    /**
     * Initiates the process of dropping out, potentially applying drop-out protection.
     */
    @Override
    public void DropOut() {
        Logger.invokeMethod(this, List.of());

        if (dropOutProtections.isEmpty()) {
            droppedOut = true;
            GameManager.GetInstance().DropOutStudent(this);
        } else {
            var dropOutProtection = dropOutProtections.poll();
            dropOutProtection.Affect();
        }

        Logger.returnVoid();
    }

    /**
     * Reacts to winning the game.
     *
     * @param sr The slide rule item that caused the student to win.
     */
    @Override
    public void NotifyStudentWin(SlideRule sr) {
        Logger.invokeMethod(this, List.of());

        GameManager.GetInstance().Win();

        Logger.returnVoid();
    }
}
