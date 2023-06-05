package fr.esgi.task;


import java.util.Scanner;

public class ConsoleManager implements IConsoleManager {

    private final Scanner scanner = new Scanner(System.in);

    public String ReadLine() {
        return scanner.nextLine();
    }

    public void WriteLine(String value) {
        System.out.println(value);

    }

    public Long ReadLong() {
        Long value = null;
        while (value == null) {
            try {
                value = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erreur lors de la saisie.");
            }
        }
        return value;
    }
}
