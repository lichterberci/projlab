package lab.proj.utils;

import java.util.Scanner;

/**
 * A utility class for interacting with the user by asking questions and receiving input.
 */
public class AskTheUser {

    /**
     * Asks the user for a number.
     * @param question The question to ask.
     * @return The number entered by the user.
     */
    public static int number(String question) {
        System.out.print(question);
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * Asks the user for a decision (yes or no).
     * @param question The question to ask.
     * @return True if the user answers "igen" or "i", false otherwise.
     */
    public static boolean decision(String question) {
        System.out.print(question);
        System.out.println(" (igen/nem)");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine().trim();
        return answer.equalsIgnoreCase("igen") || answer.equalsIgnoreCase("i");
    }
}
