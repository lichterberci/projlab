package lab.proj.model;

import java.util.Collections;

/**
 * A class representing a visitor specifically for towels in the game environment.
 * Towel visitors can visit both students and teachers.
 */
public class TowelVisitor implements ActorVisitor {

    public TowelVisitor() {
        Logger.createObject(this);
    }

    /**
     * Visits a student actor.
     * This method is currently empty.
     *
     * @param s The student actor to visit.
     */
    @Override
    public void VisitStudent(Student s) {
        Logger.invokeMethod(this, Collections.singletonList(s));

        // Empty method

        Logger.returnVoid();
    }

    /**
     * Visits a teacher actor.
     * This method is currently empty.
     *
     * @param t The teacher actor to visit.
     */
    @Override
    public void VisitTeacher(Teacher t) {
        Logger.invokeMethod(this, Collections.singletonList(t));

        t.Stun();

        Logger.returnVoid();
    }

    /**
     * Defines the behavior of visiting a cleaning lady actor.
     * This method is currently empty.
     *
     * @param cl The teacher actor being visited.
     */
    @Override
    public void VisitCleaningLady(CleaningLady cl) {
        Logger.invokeMethod(this, Collections.singletonList(cl));

        // Empty.

        Logger.returnVoid();
    }
}
