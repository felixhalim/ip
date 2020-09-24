package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.List;

public class DoneCommand extends Command {
    private int taskIdentifier;

    public DoneCommand(String command) {
        try {
            taskIdentifier = Integer.parseInt(command.split(" ")[1]);
        } catch (NumberFormatException e) {
            System.out.println(">.< The task identifier supposed to be number! Here is the correct format" +
                    " \"done <number>\"");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(">.< You can't leave the task identifier empty! The format supposed to " +
                    "be \"done <number>\"");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> list = tasks.getList();
        if (taskIdentifier > 0 && taskIdentifier <= list.size()) {
            list.get(taskIdentifier - 1).markAsDone();
            System.out.println("Okay! I've marked this task as done: Keep on going!");
            System.out.println(list.get(taskIdentifier - 1).getTask());
        } else {
            System.out.println("Sorry, I can't find the task :(");
        }
        tasks.setList(list);
    }
}
