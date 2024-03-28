package lab.proj.testUseCases;

import lab.proj.model.GasPoisoning;
import lab.proj.model.Room;
import lab.proj.model.Teacher;
import lab.proj.utils.IndentedDebugPrinter;

public class GasPoisTeacher implements TestUseCase {
    protected static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        Teacher t1 = new Teacher();
        Teacher t2 = new Teacher();
        Room r2 = new Room();

        t1.SetLocation(r2);
        t2.SetLocation(r2);

        GasPoisoning g = new GasPoisoning();
        r2.AddEffect(g);

        r2.TimePassed();
    }
}
