package lab.proj.testUseCases;

public class StudentSwitchesRoomsNoProtection extends OneTeacherOneStudent {


    @Override
    public void runUseCase() {
        super.runUseCase();
        boolean success = s.UseDoor(d);
        t1.DropOutAll();
    }
}
