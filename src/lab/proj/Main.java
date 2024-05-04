package lab.proj;

import lab.proj.controller.GameManager;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.model.Teacher;
import lab.proj.ui.Application;
import lab.proj.ui.screens.MenuScreen;
import lab.proj.utils.ActionManager;
import lab.proj.utils.SequenceDiagramPrinter;
import java.io.OutputStream;
import java.io.PrintStream;


public class Main {
    public static void main(String[] args) {
        testModel();
    }

    private static void testModel() {
//        // Suppress output from debug printer
//        SequenceDiagramPrinter.resetInstance(new PrintStream(OutputStream.nullOutputStream()));
//        // Run interpreter loop
//        new ActionManager(System.in, System.out).runCommandInterpreter();

        GameManager.GetInstance().ResetGame();
        GameManager.GetInstance().CreateStudent("Berci");
        GameManager.GetInstance().CreateStudent("Dani");
        GameManager.GetInstance().CreateStudent("Karesz");
        GameManager.GetInstance().CreateStudent("Soma");
        GameManager.GetInstance().CreateStudent("Zoli");

        Application.GetInstance().NavigateToMenu();
        MenuScreen.GetInstance().UpdateUI(GameManager.GetInstance().GetStudents());
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