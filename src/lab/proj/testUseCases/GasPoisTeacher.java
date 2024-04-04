package lab.proj.testUseCases;

import lab.proj.model.GasPoisoning;
import lab.proj.model.Room;
import lab.proj.model.Teacher;
import lab.proj.utils.SequenceDiagramPrinter;

public class GasPoisTeacher implements TestUseCase {
    protected static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    @Override
    public void runUseCase() {
        Teacher t1 = new Teacher();
        Teacher t2 = new Teacher();
        Room r2 = new Room();

        r2.AddActor(t1);
        r2.AddActor(t2);

        GasPoisoning g = new GasPoisoning();
        r2.AddEffect(g);

        r2.TimePassed();
    }
}
