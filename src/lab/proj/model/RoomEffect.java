package lab.proj.model;

/**
 * An abstract class representing a room effect in the game environment.
 * Room effects extend the functionality of basic entities and are associated with a specific room.
 */
public abstract class RoomEffect implements Entity {

    /**
     * The location (room) associated with the room effect.
     */
    protected Room location;

    /**
     * Sets the location (room) associated with the room effect.
     *
     * @param r The room to set as the location for the room effect.
     */
    public void SetLocation(Room r) {
        
        location = r;
    }
}