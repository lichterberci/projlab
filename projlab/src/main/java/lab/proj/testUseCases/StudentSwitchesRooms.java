package lab.proj.testUseCases;

import lab.proj.model.Actor;
import lab.proj.model.Door;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class StudentSwitchesRooms extends TwoTeachersOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    protected boolean result;

    @Override
    public void runUseCase() {
        super.runUseCase();
        Logger.invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, s, "UseDoor", List.of(d));
        result = s.UseDoor(d);
        Logger.returnFromMethod(IndentedDebugPrinter.CONTROLLER, s, "UseDoor", Optional.of(result));
    }
}
