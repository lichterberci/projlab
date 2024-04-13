package lab.proj.model;

import lab.proj.utils.SequenceDiagramPrinter;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A class representing a teacher actor in the game environment.
 * Teachers can perform actions such as stunning students and initiating dropout processes.
 */
public class Teacher extends Actor {

    /**
     * The logger instance for debugging purposes.
     */
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    public Teacher() {
        Logger.createObject(this);
    }

    /**
     * Stuns the teacher.
     */
    public void Stun() {
        Logger.invokeMethod(this, List.of());

        incapacitated = true;

        Logger.returnVoid();
    }

    /**
     * Initiates the dropout process for all actors in the teacher's current location.
     * If an actor is droppable, the method calls its `DropOut` method.
     */
    public void DropOutAll() {
        Logger.invokeMethod(this, List.of());

        if (!incapacitated)
            for (Actor actor : location.GetActors())
                actor.DropOut();

        Logger.returnVoid();
    }

    /**
     * Performs actions associated with the passage of time for the teacher.
     * This method is currently empty.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        // Empty method

        Logger.returnVoid();
    }

    /**
     * Accepts a visitor, allowing it to visit the teacher.
     *
     * @param v The visitor to be accepted.
     */
    @Override
    public void VisitActor(ActorVisitor v) {
        Logger.invokeMethod(this, Collections.singletonList(v));

        v.VisitTeacher(this);

        Logger.returnVoid();
    }

    /**
     * Reacts to being shocked.
     */
    @Override
    public void Shock() {
        Logger.invokeMethod(this, List.of());

        incapacitated = true;
        DropAllItems();

        Logger.returnVoid();
    }

    /**
     * Initiates the dropout process for the teacher.
     * This method logs the attempt to drop out the teacher.
     */
    @Override
    public void DropOut() {
        Logger.invokeMethod(this, List.of());
        Logger.returnVoid();
    }

    @Override
    public void NotifyStudentWin(Item item) {
        Logger.invokeMethod(this, List.of());

        item.Drop();

        Logger.returnVoid();
    }
}
