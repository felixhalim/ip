package duke.task;

public class Deadline extends Task {
    private final String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, String date, String isDone) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Returns the details of the task.
     *
     * @return String of the task details.
     */
    @Override
    public String getTask() {
        return String.format("[D]%s (by: %s)", super.getTask(), date);
    }
}
