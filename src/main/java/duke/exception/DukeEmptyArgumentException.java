package duke.exception;

public class DukeEmptyArgumentException extends DukeException {
    public DukeEmptyArgumentException() {
    }

    public DukeEmptyArgumentException(String message) {
        super(message);
    }
}
