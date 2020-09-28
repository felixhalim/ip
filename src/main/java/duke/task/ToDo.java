package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, String isDone) {
        super(description, isDone);
    }

    /**
     * Returns the details of the task.
     *
     * @return String of the task details.
     */
    @Override
    public String getTask() {
        return String.format("[T]%s", super.getTask());
    }
}
