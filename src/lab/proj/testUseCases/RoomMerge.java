package lab.proj.testUseCases;

/**
 * A class representing a specific use case with four rooms and three doors.
 */
public class RoomMerge extends FourRoomsThreeDoors {

    /**
     * Runs the use case, invoking the superclass method and activating the RoomMerge item.
     */
    @Override
    public void runUseCase() {
        super.runUseCase();

        r1.TimePassed();
    }
}
