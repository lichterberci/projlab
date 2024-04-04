package lab.proj.testUseCases;

import lab.proj.model.Towel;
import lab.proj.utils.SequenceDiagramPrinter;

public class StudentStunsTeacher extends OneTeacherOneStudent {
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        Towel t = new Towel();
        boolean success = t.PickUp(s);

        success = s.UseDoor(d);

        s.TimePassed();
    }
}
