package seedu.duke.commons.exceptions.listexceptions;

import seedu.duke.commons.exceptions.DukeException;

/**
 * Defines a InvalidTaskListSizeException object that extends the DukeException class.
 * Signals that a specified TaskList size is invalid. (eg. not a natural number)
 */
public class InvalidTaskListSizeException extends DukeException {

    /**
     * Creates an instance of InvalidTaskListSizeException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public InvalidTaskListSizeException(String message) {
        super(message);
    }

    /**
     * Creates an instance of InvalidTaskListSizeException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public InvalidTaskListSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
