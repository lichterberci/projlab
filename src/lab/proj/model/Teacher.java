package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a teacher actor in the game environment.
 * Teachers can perform actions such as stunning students and initiating dropout processes.
 */
public class Teacher extends Actor {

    /**
     * The logger instance for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    public Teacher() {
        Logger.createObject(this);
    }

    /**
     * Stuns the teacher.
     * This method is currently empty.
     */
    public void Stun() {
        Logger.invokeMethod(this, List.of());

        incapacitated = true;

        Logger.returnVoid();
    }

    /**
     * Initiates the dropout process for a specific student.
     * This method is currently empty.
     *
     * @param s The student to be dropped out.
     */
    public void DropOutStudent(Student s) {
        Logger.invokeMethod(this, Collections.singletonList(s));

        // Empty method

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
     * This method is currently empty.
     */
    @Override
    public void Shock() {
        Logger.invokeMethod(this, List.of());

        incapacitated = true;

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
}
