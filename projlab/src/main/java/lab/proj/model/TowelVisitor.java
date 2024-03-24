package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.Optional;

/**
 * A class representing a visitor specifically for towels in the game environment.
 * Towel visitors can visit both students and teachers.
 */
public class TowelVisitor implements ActorVisitor {

    /**
     * Visits a student actor.
     * This method is currently empty.
     * @param s The student actor to visit.
     */
    @Override
    public void VisitStudent(Student s) {
        // Empty method
    }

    /**
     * Visits a teacher actor.
     * This method is currently empty.
     * @param t The teacher actor to visit.
     */
    @Override
    public void VisitTeacher(Teacher t) {
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, t, "Stun", new ArrayList<>());
        t.Stun();
        IndentedDebugPrinter.getInstance().returnFromMethod(this, t, "Stun", Optional.empty());
    }
}
