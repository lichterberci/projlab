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
        Room r1 = GameManager.GetInstance().CreateRoom();
        Room r2 = GameManager.GetInstance().CreateRoom();
        Room r3 = GameManager.GetInstance().CreateRoom();
        GameManager.GetInstance().CreateStudent("Berci").SetLocation(r1);
        GameManager.GetInstance().CreateStudent("Dani").SetLocation(r1);
        GameManager.GetInstance().CreateStudent("Karesz").SetLocation(r1);
        GameManager.GetInstance().CreateStudent("Soma").SetLocation(r1);
        GameManager.GetInstance().CreateStudent("Zoli").SetLocation(r1);
        GameManager.GetInstance().CreateTeacher().SetLocation(r2);
        GameManager.GetInstance().CreateTeacher().SetLocation(r2);
        GameManager.GetInstance().CreateTeacher().SetLocation(r2);
        GameManager.GetInstance().CreateTeacher().SetLocation(r2);
        GameManager.GetInstance().CreateCleaningLady().SetLocation(r3);

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