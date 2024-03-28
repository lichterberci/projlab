package lab.proj.testUseCases;

import lab.proj.utils.IndentedDebugPrinter;

public class StudentSwitchesRoomsNoProtection extends OneTeacherOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();
        boolean success = s.UseDoor(d);
        Logger.returnValue(success);
        t1.DropOutAll();
    }
}
