package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeEmptyArgumentException;
import duke.task.Task;

import java.util.List;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String command) throws DukeEmptyArgumentException {
        String parsedResponse;
        try {
            parsedResponse = command.split(" ", 2)[1];
            this.keyword = parsedResponse;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("@.@ I'm confused! Please check your argument");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Shows the List of Task where each Task element contain the search keyword.
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
            System.out.printf("Here %s the matching %s in your list:%n", listPlurality, taskPlurality);
            int startingNumber = 1;
            for (Task task : list) {
                if (task.getTask().contains(keyword)) {
                    System.out.println(startingNumber + "." + task.getTask());
                    startingNumber++;
                }
            }
            if (startingNumber == 1) {
                System.out.println("Sorry I can't find any task with the keyword \"" + keyword + "\"");
            }
        }
    }
}
