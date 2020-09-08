package duke.task;

public class Deadline extends Task {
    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTask() {
        return String.format("[D]%s (by: %s)", super.getTask(), date);
    }
}
