package duke.task;

public class Event extends Task {
    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTask() {
        return String.format("[E]%s (at: %s)", super.getTask(), date);
    }
}
