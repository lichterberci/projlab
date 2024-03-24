package lab.proj.model;


public abstract class RoomEffect implements Entity {
    Room location;

    public void SetLocation(Room r) {
        location = r;
    }
}
