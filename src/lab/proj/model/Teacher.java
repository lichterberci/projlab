package lab.proj.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class representing a teacher actor in the game environment.
 * Teachers can perform actions such as stunning students and initiating dropout processes.
 */
public class Teacher extends Actor {

    private boolean stunned = false;
    private Set<Actor> alreadyDroppedOut = new HashSet<>();

    public Teacher() {
        Logger.createObject(this);
    }

    /**
     * Stuns the teacher.
     */
    public void Stun() {
        Logger.invokeMethod(this, List.of());

        stunned = true;

        Logger.returnVoid();
    }

    @Override
    public boolean IsBlocked() {
        Logger.invokeMethod(this, List.of());
        boolean isBlocked = super.IsBlocked() || stunned;
        Logger.returnValue(isBlocked);
        return isBlocked;
    }

    /**
     * Initiates the dropout process for all actors in the teacher's current location.
     * If an actor is droppable, the method calls its `DropOut` method.
     */
    public void DropOutAll() {
        Logger.invokeMethod(this, List.of());

        if (!IsBlocked())
            for (Actor actor : location.GetActors())
                if (!alreadyDroppedOut.contains(actor))
                    actor.DropOut();

        alreadyDroppedOut = new HashSet<>(location.GetActors());

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
     * Initiates the dropout process for the teacher.
     * This method logs the attempt to drop out the teacher.
     */
    @Override
    public void DropOut() {
        Logger.invokeMethod(this, List.of());
        Logger.returnVoid();
    }

    @Override
    public void NotifyStudentWin(SlideRule sr) {
        Logger.invokeMethod(this, List.of());

        sr.Drop();

        Logger.returnVoid();
    }
}
