package lab.proj.model;

/**
 * An interface defining the visitor pattern for visiting actors in the game environment.
 * Implementations of this interface can define different behaviors for visiting different types of actors.
 */
public interface ActorVisitor {

    /**
     * Defines the behavior of visiting a student actor.
     * @param s The student actor to be visited.
     */
    void VisitStudent(Student s);

    /**
     * Defines the behavior of visiting a teacher actor.
     * @param t The teacher actor to be visited.
     */
    void VisitTeacher(Teacher t);
}
