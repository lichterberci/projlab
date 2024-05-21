package lab.proj.testUseCases;

/**
 * A class representing a specific use case with two teachers and one student.
 */
public record TestUseCaseWrapper(String title, Class<? extends TestUseCase> testUseCaseClass) {
}
