package duke.task;

public class Event extends Task {
    private final String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public Event(String description, String date, String isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String getTask() {
        return String.format("[E]%s (at: %s)", super.getTask(), date);
    }
}
