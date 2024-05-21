package lab.proj.testUseCases;

import lab.proj.model.GasPoisoning;
import lab.proj.model.Room;
import lab.proj.model.Teacher;
import lab.proj.utils.SequenceDiagramPrinter;

/**
 * A class representing the usage of a GasPoisoning item in a specific use case.
 * This use case involves two teachers and a gas poisoning item.
 */
public class GasPoisTeacher implements TestUseCase {
    /**
     * The logger for the use case.
     */
    protected static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();


    /**
     * Runs the use case, creating the teachers and gas poisoning item and setting the teachers in the room.
     */
    @Override
    public void runUseCase() {
        Teacher t1 = new Teacher();
        Teacher t2 = new Teacher();
        Room r2 = new Room();

        t1.SetLocation(r2);
        t2.SetLocation(r2);

        GasPoisoning g = new GasPoisoning();
        g.SetLocation(r2);

        r2.TimePassed();
    }
}
