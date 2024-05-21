package lab.proj.testUseCases;

/**
 * A class representing a specific use case with three rooms and two doors.
 */
public class RoomSplit extends ThreeRoomsTwoDoors {

    /**
     * Runs the use case, invoking the superclass method and activating the RoomSplit item.
     */
    @Override
    public void runUseCase() {
        super.runUseCase();
        r1.TimePassed();
    }
}
