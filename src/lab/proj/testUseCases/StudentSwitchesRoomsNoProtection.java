package lab.proj.testUseCases;

/**
 * A class representing a specific use case with one teacher and one student.
 */
public class StudentSwitchesRoomsNoProtection extends OneTeacherOneStudent {

    /**
     * Runs the use case, invoking the superclass method and switching rooms.
     */
    @Override
    public void runUseCase() {
        super.runUseCase();
        boolean success = s.UseDoor(d);
        t1.DropOutAll();
    }
}
