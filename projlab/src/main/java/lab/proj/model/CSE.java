package lab.proj.model;

/**
 * A class representing a CSE (Code of Studies and Exams) item in the game environment.
 * CSE items extend the functionality of living items.
 */
public class CSE extends LivingItem {

    /**
     * Performs actions associated with the passage of time.
     * This method is currently empty for CSE items.
     */
    @Override
    public void TimePassed() {
        // No actions for CSE on time passage
    }

    /**
     * Performs the action of using the CSE item.
     * This method is currently empty for CSE items.
     */
    @Override
    public void Use() {
        // No specific action for using CSE items
    }
}
