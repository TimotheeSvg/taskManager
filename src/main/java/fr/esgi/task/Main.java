package fr.esgi.task;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(new ConsoleManager(), new TaskList());
        taskManager.start();

    }
}
