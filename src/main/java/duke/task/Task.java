package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String isDone) {
        this.description = description;
        this.isDone = isDone.equals("1");
    }

    /**
     * Returns the status icon of the task.
     *
     * @return String of the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "V" : "X"); //return V or X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the details of the task.
     *
     * @return String of the task details.
     */
    public String getTask() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}