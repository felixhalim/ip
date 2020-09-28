package duke;

import duke.command.*;
import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeIllegalCommandException;

public class Parser {
    public static Command parse(String command) {
        try {
            if (command.equals("bye")) {
                return new ExitCommand();
            }
            if (command.equals("list")) {
                return new ListCommand();
            }
            if (command.startsWith("done ")) {
                return new DoneCommand(command);
            }
            if (command.startsWith("delete ")) {
                return new DeleteCommand(command);
            }
            if (command.startsWith("find ")) {
                return new FindCommand(command);
            } else {
                return new AddCommand(command);
            }
        } catch (DukeIllegalCommandException | DukeEmptyArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
