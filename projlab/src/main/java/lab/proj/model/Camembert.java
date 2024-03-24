package lab.proj.model;

/**
 * A class representing a Camembert item in the game environment.
 * Camembert's items extend the functionality of basic items.
 */
public class Camembert extends Item {

    /**
     * Performs actions associated with the passage of time.
     * This method is currently empty for Camembert items.
     */
    @Override
    public void TimePassed() {
        // No actions for Camembert on time passage
    }
}
