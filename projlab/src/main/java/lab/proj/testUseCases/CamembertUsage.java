package lab.proj.testUseCases;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.Optional;

/**
 * A class representing the usage of a Camembert item in a specific use case.
 * This use case involves two teachers and one student.
 */
public class CamembertUsage extends TwoTeachersOneStudent {

    /** The logger instance for debugging purposes. */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * Runs the use case, invoking the superclass method and activating the Camembert item.
     */
    @Override
    public void runUseCase() {
        super.runUseCase();
        Logger.invokeObjectMethod(cm, "Activate", Collections.emptyList());
        cm.Activate();
        Logger.returnFromMethod(cm, "Activate", Optional.empty());
    }
}
