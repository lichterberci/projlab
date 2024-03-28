package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;

/**
 * A class representing a visitor that visits student and teacher actors affected by gas in the game environment.
 * This class implements the ActorVisitor interface.
 */
public class GasVisitor implements ActorVisitor {

    /**
     * A logger for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * Defines the behavior of visiting a student actor affected by gas.
     * This method invokes the "Shock" method of the student actor.
     *
     * @param s The student actor being visited.
     */
    @Override
    public void VisitStudent(Student s) {
        Logger.invokeMethod(this, List.of(s));
        s.Shock();
    }

    /**
     * Defines the behavior of visiting a teacher actor.
     * This method is currently empty.
     *
     * @param t The teacher actor being visited.
     */
    @Override
    public void VisitTeacher(Teacher t) {
        Logger.invokeMethod(this, List.of(t));
        t.Shock();
    }
}
