package lab.proj.testUseCases;

import lab.proj.utils.SequenceDiagramPrinter;

public class StudentSwitchesRoomsNoProtection extends OneTeacherOneStudent {
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();
        boolean success = s.UseDoor(d);
        t1.DropOutAll();
    }
}
