package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

/**
 * A class representing a visitor specifically for towels in the game environment.
 * Towel visitors can visit both students and teachers.
 */
public class TowelVisitor implements ActorVisitor {

    /**
     * Visits a student actor.
     * This method is currently empty.
     *
     * @param s The student actor to visit.
     */
    @Override
    public void VisitStudent(Student s) {
        
        // Empty method
    }

    /**
     * Visits a teacher actor.
     * This method is currently empty.
     *
     * @param t The teacher actor to visit.
     */
    @Override
    public void VisitTeacher(Teacher t) {
        t.Stun();
    }
}
