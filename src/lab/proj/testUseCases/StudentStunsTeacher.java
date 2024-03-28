package lab.proj.testUseCases;

import lab.proj.model.Towel;
import lab.proj.utils.IndentedDebugPrinter;

public class StudentStunsTeacher extends OneTeacherOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        Towel t = new Towel();
        Logger.createObject(t, "t");
        boolean success = t.PickUp(s);

        success = s.UseDoor(d);

        s.TimePassed();
    }
}
