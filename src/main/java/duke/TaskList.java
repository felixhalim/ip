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

    /**
     * Returns List of Task object stored in list.
     *
     * @return List of Task.
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * Sets list to the List of Task object passed.
     *
     * @param list the current List of Task.
     */
    public void setList(List<Task> list) {
        this.list = new ArrayList<>(list);
    }
}
