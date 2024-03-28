package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

/**
 * A class representing a curse effect in the game environment.
 * Curse effects extend the functionality of room effects.
 */
public class Curse extends RoomEffect {

    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * Performs actions associated with the passage of time.
     */
    @Override
    public void TimePassed() {
        
        
        List<Door> doors = location.GetDoors();
        

        boolean shouldHide = AskTheUser.decision("Érvényesül-e az átok?");
        if (shouldHide) {
            for (Door door : doors) {
                
                door.Hide();
                
            }
        }
    }
}
