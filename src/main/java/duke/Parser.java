package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeIllegalCommandException;


/**
 * A <code>Parser</code> object will parse given <code>String command</code>
 * and return the corresponding <code>Command</code> object.
 */

public class Parser {

    /**
     * Returns the corresponding command .
     * If the position is unset, NaN is returned.
     *
     * @param command the full command inputted by User.
     * @return Command object that correspond to the command.
     */
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
