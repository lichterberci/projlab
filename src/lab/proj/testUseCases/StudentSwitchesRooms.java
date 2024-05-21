package lab.proj.testUseCases;

/**
 * A class representing a specific use case with two teachers and one student.
 */
public class StudentSwitchesRooms extends TwoTeachersOneStudent {

    /**
     * Runs the use case, invoking the superclass method and switching rooms.
     */
    protected boolean result;

    /**
     * Runs the use case, invoking the superclass method and switching rooms.
     */
    @Override
    public void runUseCase() {
        super.runUseCase();
        result = s.UseDoor(d);
    }
}
