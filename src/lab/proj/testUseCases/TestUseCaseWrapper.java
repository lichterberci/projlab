package lab.proj.testUseCases;

public record TestUseCaseWrapper(String title, Class<? extends TestUseCase> testUseCaseClass) {
}
