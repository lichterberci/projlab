package lab.proj.testUseCases;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class StudentSwitchesRooms extends TwoTeachersOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();
    protected boolean result;

    @Override
    public void runUseCase() {
        super.runUseCase();
        result = s.UseDoor(d);
    }
}
