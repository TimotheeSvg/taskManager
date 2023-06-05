package fr.esgi.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(List<Task> newTasks) {
        tasks = new ArrayList<>(newTasks);
    }

    public boolean add_task(Task task) {
        try {
            this.tasks.add(task);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean delete_task(Long id) {
        for (Task task : tasks) {
            if (Objects.equals(task.getId(), id)) {
                tasks.remove(task);
                return true;
            }
        }
        return false;
    }

    public Task get_Task(Long id) {
        for (Task task : tasks) {
            if (Objects.equals(task.getId(), id)) {
                return task;
            }
        }
        return null;
    }

    public List<Task> get_active_task() {
        List<Task> activeTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isState()) {
                activeTasks.add(task);
            }
        }
        return activeTasks;
    }

    public List<Task> get_inactive_task() {
        List<Task> inactiveTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isState()) {
                inactiveTasks.add(task);
            }
        }
        return inactiveTasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
