package lab.proj.testUseCases;

import lab.proj.utils.IndentedDebugPrinter;

public class CursedRoom extends ThreeRoomsTwoDoors {

    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();
        r1.TimePassed();
    }
}
