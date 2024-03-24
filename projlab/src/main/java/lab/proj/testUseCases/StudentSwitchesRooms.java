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
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, s, "UseDoor", List.of(d));
        result = s.UseDoor(d);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, s, "UseDoor", Optional.of(result));
    }
}
