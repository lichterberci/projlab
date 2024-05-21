package lab.proj.testUseCases;

import lab.proj.model.Transistor;

/**
 * A class representing the usage of a Camembert item in a specific use case.
 * This use case involves two teachers and one student.
 */
public class TransistorPairing implements TestUseCase {
    /**
     * The logger instance for debugging purposes.
     */

    protected boolean result;

    /**
     * The first transistor.
     */
    protected Transistor t1;

    /**
     * The second transistor.
     */
    protected Transistor t2;

    /**
     * Runs the use case, invoking the superclass method and activating the Camembert item.
     */
    @Override
    public void runUseCase() {
        t1 = new Transistor();
        t2 = new Transistor();

        t1.PairWith(t2);
    }
}