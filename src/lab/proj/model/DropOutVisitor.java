package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;

/**
 * A class representing a visitor that visits student and teacher actors in the game environment.
 * This class implements the ActorVisitor interface.
 */
public class DropOutVisitor implements ActorVisitor {

    /**
     * A logger for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * Defines the behavior of visiting a student actor.
     * This method is currently empty.
     *
     * @param s The student actor being visited.
     */
    @Override
    public void VisitStudent(Student s) {
        Logger.invokeMethod(this, List.of(s));        // No specific behavior defined for visiting student actors
    }

    /**
     * Defines the behavior of visiting a teacher actor.
     * This method is currently empty.
     *
     * @param t The teacher actor being visited.
     */
    @Override
    public void VisitTeacher(Teacher t) {
        Logger.invokeMethod(this, List.of(t));        // No specific behavior defined for visiting teacher actors
    }
}
