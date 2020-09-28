package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.List;

public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Shows List of Task content.
     *
     * @param tasks   the current state of TaskList.
     * @param ui      the current state of Ui.
     * @param storage the current state of Storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> list = tasks.getList();
        int size = list.size();
        String listPlurality = size > 1 ? "are" : "is";
        String taskPlurality = size > 1 ? "tasks" : "task";
        if (size == 0) {
            System.out.println("You have nothing on your list! Why not add one :D");
        } else {
            System.out.printf("Here %s the %s in your list:%n", listPlurality, taskPlurality);
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i).getTask());
            }
        }
    }
}
