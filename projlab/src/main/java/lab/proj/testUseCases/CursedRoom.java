package lab.proj.testUseCases;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class CursedRoom extends ThreeRoomsTwoDoors {

    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, r1, "TimePassed", List.of());
        r1.TimePassed();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, r1, "TimePassed", Optional.empty());
    }
}
