package fr.esgi.task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
public class TaskListTest {
    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        task1 = new Task("Faire les courses", false);
        task2 = new Task("Faire le ménage", true);
    }

    @Test
    public void testAddTask() {
        boolean result = taskList.add_task(task1);
        Assertions.assertTrue(result, "La tâche doit être ajoutée avec succès");

        List<Task> tasks = taskList.getTasks();
        Assertions.assertEquals(1, tasks.size(), "La liste doit contenir une tâche");
        Assertions.assertEquals(task1, tasks.get(0), "La tâche ajoutée doit être présente dans la liste");
    }

    @Test
    public void testDeleteTask() {
        taskList.add_task(task1);
        taskList.add_task(task2);
        boolean result = taskList.delete_task(task1.getId());

        Assertions.assertTrue(result, "La tâche doit être supprimée avec succès");
        List<Task> tasks = taskList.getTasks();
        Assertions.assertEquals(1, tasks.size(), "La liste doit contenir une tâche");
        Assertions.assertEquals(task2, tasks.get(0), "La tâche supprimée ne doit plus être présente dans la liste");
    }

}
