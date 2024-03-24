package lab.proj.model;

/**
 * A class representing a transistor item in the game environment.
 * Transistors can be paired with other transistors and are subject to the passage of time.
 */
public class Transistor extends Item {

    /** The location (room) of the transistor. */
    private Room location;

    /** The paired transistor. */
    private Transistor pair;

    /**
     * Drops the transistor.
     * This method is currently empty.
     */
    public void Drop() {
        // Empty method
    }

    /**
     * Pairs the transistor with another transistor.
     * This method is currently empty.
     * @param t The transistor to pair with.
     */
    public void PairWith(Transistor t) {
        // Empty method
    }

    /**
     * Performs actions associated with the passage of time for the transistor.
     * This method is currently empty.
     */
    @Override
    public void TimePassed() {
        // Empty method
    }
}
