package fr.esgi.task;


public class Task {

    private static Long compteur = 0L;
    private final Long id;

    private String description;

    private boolean state;

    public Task() {
        id = ++compteur;
    }
    public Task(String description) {
        this();
        this.description = description;
    }

    public Task(String description, boolean state) {
        this(description);
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return id + " - " + description;
    }
}
