package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = new ArrayList<>(list);
    }

    public List<Task> getList() {
        return list;
    }

    public void setList(List<Task> list) {
        this.list = new ArrayList<>(list);
    }
}
