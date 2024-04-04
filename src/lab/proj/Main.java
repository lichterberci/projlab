package lab.proj;

import lab.proj.testUseCases.TestUseCaseRunner;
import lab.proj.utils.AskTheUser;
import lab.proj.utils.SequenceDiagramPrinter;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        SequenceDiagramPrinter.resetInstance(System.out);

        final List<String> availableUseCases = TestUseCaseRunner.getAvailableUseCases();

        StringBuilder questionBuilder = new StringBuilder("Melyik Use-Case-t futtassuk?\n");
        for (int i = 1; i <= availableUseCases.size(); i++)
            questionBuilder.append(String.format("\t%d. %s%n", i, availableUseCases.get(i - 1)));
        String question = questionBuilder.toString();

        int selectedUseCase = AskTheUser.number(question) - 1;
        TestUseCaseRunner.runTest(selectedUseCase);
    }
}