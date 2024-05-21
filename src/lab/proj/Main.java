package lab.proj;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.utils.Randomware;
import lab.proj.utils.SequenceDiagramPrinter;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Main class of the project
 */
public class Main {
    /**
     * Main method of the project
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Randomware.SetSeed(75);
        testModel();
    }

    /**
     * Tests the model
     */
    private static void testModel() {
//        // Suppress output from debug printer
        SequenceDiagramPrinter.resetInstance(new PrintStream(OutputStream.nullOutputStream()));
//        // Run interpreter loop
//        new ActionManager(System.in, System.out).runCommandInterpreter();

        GameManager.GetInstance().ResetGame();
//        GameManager.GetInstance().CreateStudent("Berci");
//        GameManager.GetInstance().CreateStudent("Dani");
//        GameManager.GetInstance().CreateStudent("Karesz");
//        GameManager.GetInstance().CreateStudent("Soma");
        GameManager.GetInstance().CreateStudent("Zoli");

        Application.GetInstance().RenderMenuScreen();
    }

//    private static void testUseCases() {
//        SequenceDiagramPrinter.resetInstance(System.out);
//
//        final List<String> availableUseCases = TestUseCaseRunner.getAvailableUseCases();
//
//        StringBuilder questionBuilder = new StringBuilder("Melyik Use-Case-t futtassuk?\n");
//        for (int i = 1; i <= availableUseCases.size(); i++)
//            questionBuilder.append(String.format("\t%d. %s%n", i, availableUseCases.get(i - 1)));
//        String question = questionBuilder.toString();
//
//        int selectedUseCase = AskTheUser.number(question) - 1;
//        TestUseCaseRunner.runTest(selectedUseCase);
//    }
}