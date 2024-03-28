package lab.proj.testUseCases;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class StudentSwitchesRoomsNoProtection extends OneTeacherOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();
        Logger.invokeMethod(s, "UseDoor", List.of(d));
        boolean success = s.UseDoor(d);
        Logger.returnValue(success);
        Logger.invokeMethod(t1, "DropOutAll", List.of());
        t1.DropOutAll();
        Logger.returnVoid();
    }
}
