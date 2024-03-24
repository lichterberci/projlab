package lab.proj.model;

/**
 * A class representing a visitor that visits student and teacher actors in the game environment.
 * This class implements the ActorVisitor interface.
 */
public class DropOutVisitor implements ActorVisitor {

    /**
     * Defines the behavior of visiting a student actor.
     * This method is currently empty.
     * @param s The student actor being visited.
     */
    @Override
    public void VisitStudent(Student s) {
        // No specific behavior defined for visiting student actors
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
