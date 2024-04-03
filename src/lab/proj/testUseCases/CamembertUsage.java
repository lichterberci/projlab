package lab.proj.testUseCases;

/**
 * A class representing the usage of a Camembert item in a specific use case.
 * This use case involves two teachers and one student.
 */
public class CamembertUsage extends TwoTeachersOneStudent {

    /**
     * Runs the use case, invoking the superclass method and activating the Camembert item.
     */
    @Override
    public void runUseCase() {
        super.runUseCase();
        cm.Activate();
    }
}
