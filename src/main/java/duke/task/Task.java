package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "V" : "X"); //return V or X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTask() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}