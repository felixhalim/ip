package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeIllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.List;

public class AddCommand extends Command {
    private final String description;
    private final String type;
    private String date;

    public AddCommand(String command) throws DukeEmptyArgumentException, DukeIllegalCommandException {
        String parsedResponse;
        try {
            this.type = command.split(" ", 2)[0];
            parsedResponse = command.split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("@.@ I'm confused! Please check your argument");
        }
        if (command.startsWith("todo")) {
            // e.g. : todo finish ip
            this.description = parsedResponse;
        } else if (command.startsWith("deadline")) {
            // e.g. : deadline finish ip /by tomorrow
            try {
                String description = parsedResponse.split(" /by ")[0];
                String date = parsedResponse.split(" /by ")[1];
                this.description = description;
                this.date = date;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeEmptyArgumentException("@.@ I'm confused! Please check your argument");
            }
        } else if (command.startsWith("event")) {
            // e.g. : event tp meeting /at monday, 7pm-8pm
            try {
                String description = parsedResponse.split(" /at ")[0];
                String date = parsedResponse.split(" /at ")[1];
                this.description = description;
                this.date = date;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeEmptyArgumentException("@.@ I'm confused! Please check your argument");
            }
        } else {
            throw new DukeIllegalCommandException("@.@ Oops, I don't understand your command");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> list = tasks.getList();
        int size = list.size();
        if (type.equalsIgnoreCase("todo")) {
            list.add(new ToDo(description));
        } else if (type.equalsIgnoreCase("deadline")) {
            list.add(new Deadline(description, date));
        } else if (type.equalsIgnoreCase("event")) {
            list.add(new Event(description, date));
        } else {
            return;
        }
        size = list.size();
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(size - 1).getTask());
        System.out.printf("Now you have %d %s in the list.%n", size, size > 1 ? "tasks" : "task");
        tasks.setList(list);
    }
}
