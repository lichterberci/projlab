package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.Optional;

/**
 * A class representing a visitor that visits student and teacher actors affected by gas in the game environment.
 * This class implements the ActorVisitor interface.
 */
public class GasVisitor implements ActorVisitor {

    /** A logger for debugging purposes. */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * Defines the behavior of visiting a student actor affected by gas.
     * This method invokes the "Shock" method of the student actor.
     * @param s The student actor being visited.
     */
    @Override
    public void VisitStudent(Student s) {
        Logger.invokeObjectMethod(s, "Shock", Collections.emptyList());
        s.Shock();
        Logger.returnFromMethod(s, "Shock", Optional.empty());
    }

    /**
     * Defines the behavior of visiting a teacher actor.
     * This method is currently empty.
     * @param t The teacher actor being visited.
     */
    @Override
    public void VisitTeacher(Teacher t) {
        // No specific behavior defined for visiting teacher actors
    }
}
