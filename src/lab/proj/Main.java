package lab.proj;

import lab.proj.testUseCases.TestUseCaseRunner;
import lab.proj.utils.ActionManager;
import lab.proj.utils.AskTheUser;
import lab.proj.utils.SequenceDiagramPrinter;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
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

        SequenceDiagramPrinter.resetInstance(new PrintStream(OutputStream.nullOutputStream()));

        ActionManager actionManager = new ActionManager();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String inputLine = scanner.nextLine();

                if (inputLine.equals("exit")) {
                    break;
                }

                String[] parts = inputLine.split(" ");
                String objectName = parts[0];
                String actionName = parts[1];
                List<String> arguments = List.of(parts).subList(2, parts.length);

                try {
                    actionManager.performAction(objectName, actionName, arguments);
                } catch (Exception e) {
                    System.out.println("Hiba történt: " + e.getMessage());
                }
            }
        }
    }
}