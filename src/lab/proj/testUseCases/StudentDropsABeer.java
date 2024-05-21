package lab.proj.testUseCases;

/**
 * A class representing a specific use case with two teachers and one student.
 */
public class StudentDropsABeer extends TwoTeachersOneStudent {


    /**
     * Runs the use case, invoking the superclass method and dropping the BeerMug item.
     */
    @Override
    public void runUseCase() {
        super.runUseCase();
        b.Drop();
    }
}
