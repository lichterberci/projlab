package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.Towel;
import lab.proj.model.TowelVisitor;
import lab.proj.utils.IndentedDebugPrinter;


import java.util.List;
import java.util.Optional;

public class StudentStunsTeacher extends OneTeacherOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();

        Towel t = new Towel();
        Logger.createObject(IndentedDebugPrinter.MAIN, t, "t");
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t, "PickUp", List.of(s));
        boolean success = t.PickUp(s);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t, "PickUp", Optional.of(success));

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, s, "UseDoor", List.of(d));
        success = s.UseDoor(d);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, s, "UseDoor", Optional.of(success));

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, s, "TimePassed", List.of());
        s.TimePassed();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, s, "TimePassed", Optional.empty());
    }
}
