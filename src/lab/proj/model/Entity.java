package lab.proj.model;

/**
 * An interface representing an entity in the game environment.
 * Entities are objects that can be affected by the passage of time.
 */
public interface Entity {

    /**
     * Performs actions associated with the passage of time.
     * Each implementing class must define its own behavior for this method.
     */
    void TimePassed();
}
