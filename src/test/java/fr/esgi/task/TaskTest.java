package fr.esgi.task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testCreateTask() {
        Task task = new Task("Faire les courses", false);

        Assertions.assertNotNull(task.getId(), "L'identifiant de la tâche ne doit pas être nul");
        Assertions.assertEquals("Faire les courses", task.getDescription(), "La description de la tâche doit être 'Faire les courses'");
        Assertions.assertFalse(task.isState(), "L'état de la tâche doit être false");
    }

    @Test
    public void testSetDescription() {
        Task task = new Task("Faire les courses", false);
        task.setDescription("Acheter du lait");
        Assertions.assertEquals("Acheter du lait", task.getDescription(), "La description de la tâche doit être 'Acheter du lait'");
    }

    @Test
    public void testSetState() {
        Task task = new Task("Faire les courses", false);
        task.setState(true);
        Assertions.assertTrue(task.isState(), "L'état de la tâche doit être true");
    }
}
