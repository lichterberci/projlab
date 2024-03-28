package lab.proj.testUseCases;

import lab.proj.utils.IndentedDebugPrinter;

public class StudentDropsABeer extends TwoTeachersOneStudent {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();
        b.Drop();
    }
}
