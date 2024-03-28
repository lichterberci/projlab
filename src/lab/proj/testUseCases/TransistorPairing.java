package lab.proj.testUseCases;

import lab.proj.model.Transistor;
import lab.proj.utils.IndentedDebugPrinter;

/**
 * A class representing the usage of a Camembert item in a specific use case.
 * This use case involves two teachers and one student.
 */
public class TransistorPairing implements TestUseCase {
    /**
     * The logger instance for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();
    protected boolean result;

    protected Transistor t1;
    protected Transistor t2;

    /**
     * Runs the use case, invoking the superclass method and activating the Camembert item.
     */
    @Override
    public void runUseCase() {
        t1 = new Transistor();
        t2 = new Transistor();

        Logger.createObject(t1, "t1");
        Logger.createObject(t2, "t2");

        t1.PairWith(t2);
    }
}