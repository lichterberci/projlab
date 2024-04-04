package lab.proj.testUseCases;

import lab.proj.utils.SequenceDiagramPrinter;

public class StudentSwitchesRooms extends TwoTeachersOneStudent {
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();
    protected boolean result;

    @Override
    public void runUseCase() {
        super.runUseCase();
        result = s.UseDoor(d);
    }
}
