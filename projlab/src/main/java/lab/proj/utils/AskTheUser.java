package lab.proj.utils;

import java.util.Scanner;

public class AskTheUser {

    public static int number(String question) {
        System.out.print(question);
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static boolean decision(String question) {
        System.out.print(question);
        System.out.println(" (igen/nem)");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine().trim();
        return answer.equalsIgnoreCase("igen") || answer.equalsIgnoreCase("i");
    }
}
