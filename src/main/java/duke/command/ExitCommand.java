package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Shows the farewell message.
     *
     * @param tasks   the current state of TaskList.
     * @param ui      the current state of Ui.
     * @param storage the current state of Storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewells();
    }
}
