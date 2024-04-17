package lab.proj.testUseCases;

public class StudentSwitchesRooms extends TwoTeachersOneStudent {

    protected boolean result;

    @Override
    public void runUseCase() {
        super.runUseCase();
        result = s.UseDoor(d);
    }
}
