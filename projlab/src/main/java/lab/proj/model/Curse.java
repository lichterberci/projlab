package lab.proj.model;

/**
 * A class representing a curse effect in the game environment.
 * Curse effects extend the functionality of room effects.
 */
public class Curse extends RoomEffect {

    /**
     * Performs actions associated with the passage of time.
     * This method is currently empty for curse effects.
     */
    @Override
    public void TimePassed() {
        // No actions for curse effects on time passage
    }
}
