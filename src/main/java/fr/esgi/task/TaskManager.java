package fr.esgi.task;
import java.util.List;

import java.util.Scanner;

public class TaskManager {

    private final IConsoleManager consoleManager;
    private final TaskList taskList;

    public TaskManager(IConsoleManager consoleManager, TaskList taskList) {
        this.consoleManager = consoleManager;
        this.taskList = taskList;
    }

    public void start(){
        Long menuResponse = 0L;
        boolean isDone = false;
        while (!isDone) {
            consoleManager.WriteLine("""
                        Choisir une action ?
                        1 - Liste des tâches
                        2 - Ajout d'une tâche
                        3 - Supprimer une tâche
                        4 - Noter une tâche comme finie
                        5 - Quitter""");

            menuResponse = consoleManager.ReadLong();

            switch (Integer.parseInt(menuResponse.toString())) {
                case 1 -> {
                    consoleManager.WriteLine("Liste des tâches :");
                    List<Task> tasks = taskList.getTasks();
                    tasks.forEach(task -> consoleManager.WriteLine(task.toString()));
                }
                case 2 -> {
                    consoleManager.WriteLine("Ajout d'une tâche, Veuillez saisir la description: ");
                    String taskDescription = consoleManager.ReadLine();
                    Task task = new Task(taskDescription);
                    taskList.add_task(task);
                    consoleManager.WriteLine("Tâche ajoutée !");
                }
                case 3 -> {
                    List<Task> tasks = taskList.getTasks();
                    tasks.forEach(task -> consoleManager.WriteLine(task.toString()));
                    taskList.getTasks().forEach(task -> consoleManager.WriteLine(task.toString()));
                    consoleManager.WriteLine("Saisir la tâche à supprimer: ");
                    Long idTask = consoleManager.ReadLong();

                    Task taskSelect = taskList.get_Task(idTask);

                    if (taskSelect != null) {
                        taskList.delete_task(idTask);
                        consoleManager.WriteLine("Tâche supprimée !");
                    } else {
                        consoleManager.WriteLine("Mauvaise saisie");
                    }
                }
                case 4 -> {
                    List<Task> tasks = taskList.getTasks();
                    tasks.forEach(task -> consoleManager.WriteLine(task.toString()));
                    consoleManager.WriteLine("Saisir la tâche qui à été compléter: ");
                    Long idTask = consoleManager.ReadLong();
                    Task taskSelect = taskList.get_Task(idTask);

                    if (taskSelect != null){
                        taskSelect.setState(true);
                        consoleManager.WriteLine("Tâche fini !");
                    } else {
                        consoleManager.WriteLine("Mauvaise saisie");

                    }
                }
                case 5 -> {
                    isDone = true;
                    consoleManager.WriteLine("Vous avez quitter...");
                }
                default -> consoleManager.WriteLine("Mauvaise saisie");
            }
        }
    }
}