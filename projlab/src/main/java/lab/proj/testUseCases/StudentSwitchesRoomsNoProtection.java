package lab.proj.testUseCases;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class StudentSwitchesRoomsNoProtection extends StudentSwitchesRooms {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();
        assert result; // after running the base case, it should be true
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN,
                t1,
                "DropOutAll",
                List.of());
        t1.DropOutAll();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN,
                s,
                "DropOutAll",
                Optional.empty());
    }
}
