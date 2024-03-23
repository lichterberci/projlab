package lab.proj;

import lab.proj.testUseCases.TestUseCaseRunner;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		IndentedDebugPrinter.resetInstance(System.out);

		final List<String> availableUseCases = TestUseCaseRunner.getAvailableUseCases();

		System.out.println("Please select a use-case from the list below:");
		availableUseCases.forEach(useCase -> System.out.printf("\t%s%n", useCase));

		Scanner scanner = new Scanner(System.in);
		String selectedUseCase = scanner.nextLine().trim();
		TestUseCaseRunner.runTest(selectedUseCase);

	}
}