package lab.proj.testUseCases;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.Optional;

public class CamembertUsage extends TwoTeachersOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();
        Logger.invokeObjectMethod(IndentedDebugPrinter.CONTROLLER, cm, "Activate", Collections.emptyList());
        cm.Activate();
        Logger.returnFromMethod(IndentedDebugPrinter.CONTROLLER, cm, "Activate", Optional.empty());
    }
}
