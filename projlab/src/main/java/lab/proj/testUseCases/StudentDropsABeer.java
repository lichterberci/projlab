package lab.proj.testUseCases;

import lab.proj.model.BeerMug;
import lab.proj.model.Student;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class StudentDropsABeer implements TestUseCase {
    @Override
    public void runUseCase() {
        var s = new Student();
        var b = new BeerMug();
        s.CollectItem(b);
        IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER,
                s,
                "s");
        IndentedDebugPrinter.getInstance().createObject(IndentedDebugPrinter.CONTROLLER,
                b,
                "b");
        IndentedDebugPrinter.getInstance().invokeObjectMethod(IndentedDebugPrinter.CONTROLLER,
                s,
                "DropItem",
                List.of(b));
        s.DropItem(b);
        IndentedDebugPrinter.getInstance().returnFromMethod(IndentedDebugPrinter.CONTROLLER,
                s,
                "DropItem",
                Optional.empty());
    }
}
