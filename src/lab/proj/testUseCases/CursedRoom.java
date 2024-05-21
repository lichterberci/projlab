package lab.proj.testUseCases;

/**
 * A class representing the usage of a CursedRoom item in a specific use case.
 * This use case involves three rooms and two doors.
 */
public class CursedRoom extends ThreeRoomsTwoDoors {

    /**
     * Runs the use case, invoking the superclass method and activating the CursedRoom item.
     */
    @Override
    public void runUseCase() {
        super.runUseCase();
        r1.TimePassed();
    }
}
