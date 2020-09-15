package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, String isDone) {
        super(description, isDone);
    }

    @Override
    public String getTask() {
        return String.format("[T]%s", super.getTask());
    }
}
