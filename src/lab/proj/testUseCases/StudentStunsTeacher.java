package lab.proj.testUseCases;

import lab.proj.model.Towel;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class StudentStunsTeacher extends OneTeacherOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        Towel t = new Towel();
        Logger.createObject(t, "t");
        Logger.invokeMethod(t, "PickUp", List.of(s));
        boolean success = t.PickUp(s);
        Logger.returnValue(success);

        Logger.invokeMethod(s, "UseDoor", List.of(d));
        success = s.UseDoor(d);
        Logger.returnValue(success);

        Logger.invokeMethod(s, "TimePassed", List.of());
        s.TimePassed();
        Logger.returnVoid();
    }
}
