package duke.validation;

import duke.exceptions.DukeException;

public class LoadValidation extends Validation {
    public static String getValidatedTaskType(String type) throws DukeException {
        if (!(type.equals("T") || type.equals("D") || type.equals("E"))) {
            throw new DukeException("Incorrect Type in Load File");
        } else {
            return type;
        }
    }

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

    public static void ensureValidTokens(String type, String[] arr) throws DukeException {
        if ((type.equals("D") || type.equals("E")) && arr.length != 4) {
            throw new DukeException("Incorrect Number of Tokens in Load File");
        } else if (type.equals("T") && arr.length != 3){
            throw new DukeException("Incorrect Number of Tokens in Load File");
        }
    }
}
