package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.List;

public class DeleteCommand extends Command {
    private int taskIdentifier;

    public DeleteCommand(String command) {
        try {
            taskIdentifier = Integer.parseInt(command.split(" ")[1]);
        } catch (NumberFormatException e) {
            System.out.println(">.< The task identifier supposed to be number! Here is the correct format" +
                    " \"delete <number>\"");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(">.< You can't leave the task identifier empty! The format supposed to " +
                    "be \"delete <number>\"");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Removes the task specified based on the task type and taskIdentifier from the TaskList.
     *
     * @param tasks   the current state of TaskList.
     * @param ui      the current state of Ui.
     * @param storage the current state of Storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> list = tasks.getList();
        if (taskIdentifier > 0 && taskIdentifier <= list.size()) {
            System.out.println("Noted. I've removed this task");
            System.out.println(" " + list.get(taskIdentifier - 1).getTask());
            list.remove(taskIdentifier - 1);
            int size = list.size();
            System.out.printf("Now you have %d %s in the list.%n", size, size > 1 ? "tasks" : "task");
        } else {
            System.out.println("Sorry, I can't find the task :(");
        }
        tasks.setList(list);
    }
}
