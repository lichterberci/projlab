package lab.proj.model;

import java.util.Collections;

import static lab.proj.model.Item.Logger;

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
}
