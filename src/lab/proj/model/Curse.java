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
        Logger.invokeObjectMethod(location, "GetDoors", List.of());
        List<Door> doors = location.GetDoors();
        Logger.returnFromMethod(location, "GetDoors", Optional.of(doors));

        boolean shouldHide = AskTheUser.decision("Érvényesül-e az átok?");
        if (shouldHide) {
            for (Door door : doors) {
                Logger.invokeObjectMethod(door, "Hide", List.of());
                door.Hide();
                Logger.returnFromMethod(door, "Hide", Optional.empty());
            }
        }
    }
}
