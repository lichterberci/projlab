package lab.proj.testUseCases;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class StudentDropsABeer extends TwoTeachersOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, b, "Drop", List.of());
        b.Drop();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, b, "Drop", Optional.empty());
    }
}
