package seedu.duke.validation;

import seedu.duke.exceptions.DukeException;

/**
 * Defines a LoadValidation class containing static methods.
 * Validates strings parsed from the file pointed to by the file path of a Storage object.
 * Inherits methods from Validation class.
 */
public class LoadValidation extends Validation {

    /**
     * Returns a validated Type of Task represented by a single-character String.
     * @param type Type of task represented by a single-character String.
     * @return Validated Type of Task based on given type of Task.
     * @throws DukeException If input type of Task is invalid or does not correspond to any known Type of Task.
     */
    public static String getValidatedTaskType(String type) throws DukeException {
        if (!(type.equals("T") || type.equals("D") || type.equals("E"))) {
            throw new DukeException("Incorrect Type in Load File");
        } else {
            return type;
        }
    }

    /**
     * Returns either 1 or 0, representing whether a Task has been marked as done (completed).
     * @param doneStatus String of either "0" or "1", with the latter representing that a Task has been marked as done.
     * @return An integer 1 or 0, representing the state of whether a Task has been marked as done in the storage file.
     * @throws DukeException If doneStatus is anything other than the String "0" or "1".
     */
    public static int getValidatedDoneStatus(String doneStatus) throws DukeException {
        try {
            int res = Integer.parseInt(doneStatus);
            if (!(res == 0 || res == 1)) {
                throw new NumberFormatException();
            }
            return res;
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid Done Status in Load File");
        }
    }

    /**
     * Ensures that there are valid number of tokens in a line of text in the Storage file representing a Task.
     * @param type String representation of Task type in the Storage file, denoted by a single letter.
     * @param arr An array containing words from a line of text separated by spaces, with elements representing tokens.
     * @throws DukeException If the number of tokens fails to correspond to that of it's Task type in the line of text.
     */
    public static void ensureValidNumberOfTokens(String type, String[] arr) throws DukeException {
        if ((type.equals("D") || type.equals("E")) && arr.length != 4) {
            throw new DukeException("Incorrect Number of Tokens in Load File");
        } else if (type.equals("T") && arr.length != 3) {
            throw new DukeException("Incorrect Number of Tokens in Load File");
        }
    }
}
