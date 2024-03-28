package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

/**
 * A class representing a teacher actor in the game environment.
 * Teachers can perform actions such as stunning students and initiating dropout processes.
 */
public class Teacher extends Actor {

    /**
     * The logger instance for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * Stuns the teacher.
     * This method is currently empty.
     */
    public void Stun() {
        
        incapacitated = true;
    }

    /**
     * Initiates the dropout process for a specific student.
     * This method is currently empty.
     *
     * @param s The student to be dropped out.
     */
    public void DropOutStudent(Student s) {
        
        // Empty method
    }

    /**
     * Initiates the dropout process for all actors in the teacher's current location.
     * If an actor is droppable, the method calls its `DropOut` method.
     */
    public void DropOutAll() {
        
        if (incapacitated) {
            return;
        }
        
        List<Actor> actorsInTheRoom = location.GetActors();
        

        for (Actor actor : actorsInTheRoom) {
            
            actor.DropOut();
            
        }
    }

    /**
     * Performs actions associated with the passage of time for the teacher.
     * This method is currently empty.
     */
    @Override
    public void TimePassed() {
        
        // Empty method
    }

    /**
     * Accepts a visitor, allowing it to visit the teacher.
     *
     * @param v The visitor to be accepted.
     */
    @Override
    public void VisitActor(ActorVisitor v) {
        
        
        v.VisitTeacher(this);
        
    }

    /**
     * Reacts to being shocked.
     * This method is currently empty.
     */
    @Override
    public void Shock() {
        
        incapacitated = true;
    }

    /**
     * Initiates the dropout process for the teacher.
     * This method logs the attempt to drop out the teacher.
     */
    @Override
    public void DropOut() {
        
    }
}
