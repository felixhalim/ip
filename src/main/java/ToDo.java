public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTask() {
        return String.format("[T]%s", super.getTask());
    }
}
