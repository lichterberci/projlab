package lab.proj.testUseCases;

import lab.proj.utils.SequenceDiagramPrinter;

public class RoomSplit extends ThreeRoomsTwoDoors {

    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        r1.TimePassed();
    }
}
