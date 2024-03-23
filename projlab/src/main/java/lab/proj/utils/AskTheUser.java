package lab.proj.utils;

import java.util.Scanner;

public class AskTheUser {
	public static boolean decision(String question) {
		System.out.print(question);
		System.out.println(" (yes/no)");
    	Scanner scanner = new Scanner(System.in);
    	String answer = scanner.nextLine().trim();
    	return answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y");
	}
}
