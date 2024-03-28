package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;


/**
 * An abstract class representing a room effect in the game environment.
 * Room effects extend the functionality of basic entities and are associated with a specific room.
 */
public abstract class RoomEffect implements Entity {

    /**
     * A logger for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * The location (room) associated with the room effect.
     */
    protected Room location;

    public RoomEffect() {
        Logger.createObject(this);
    }

    /**
     * Sets the location (room) associated with the room effect.
     *
     * @param r The room to set as the location for the room effect.
     */
    public void SetLocation(Room r) {
        Logger.invokeMethod(this, Collections.singletonList(r));

        location = r;

        Logger.returnVoid();
    }
}
