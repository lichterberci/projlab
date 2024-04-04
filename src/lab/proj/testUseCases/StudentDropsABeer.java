package lab.proj.testUseCases;

import lab.proj.utils.SequenceDiagramPrinter;

public class StudentDropsABeer extends TwoTeachersOneStudent {
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    @Override
    public void runUseCase() {
        super.runUseCase();
        b.Drop();
    }
}
