package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class GasPoisTeacher implements TestUseCase{
    protected static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        Teacher t1 = new Teacher();
        Teacher t2 = new Teacher();
        Room r2 = new Room();

        Logger.createObject(IndentedDebugPrinter.MAIN, t1, "t1");
        Logger.createObject(IndentedDebugPrinter.MAIN, t2, "t2");
        Logger.createObject(IndentedDebugPrinter.MAIN, r2, "r1");


        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t1, "SetLocation", List.of(r2));
        t1.SetLocation(r2);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t1, "SetLocation", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t2, "SetLocation", List.of(r2));
        t2.SetLocation(r2);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t2, "SetLocation", Optional.empty());


        GasPoisoning g = new GasPoisoning();
        Logger.createObject(IndentedDebugPrinter.MAIN, g, "g");

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, r2, "AddEffect", List.of(g));
        r2.AddEffect(g);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, r2, "AddEffect", Optional.empty());


        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, r2, "TimePassed", List.of());
        r2.TimePassed();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, r2, "TimePassed", Optional.empty());


    }
}
