package lab.proj.testUseCases;

public class UnimplementedTestUseCase implements TestUseCase {
    @Override
    public void runUseCase() {
        throw new UnsupportedOperationException("Test Use-Case is not implemented yet.");
    }
}
