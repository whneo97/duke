package seedu.duke.validation;

import seedu.duke.dateandtime.Date;
import seedu.duke.dateandtime.Time;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.TaskList;

/**
 * Defines a Validation class containing static methods.
 * Validates strings parsed for general purposes
 * Includes validation for commands, dates, times and lists.
 */
public class Validation {

    /**
     * Ensures a command not meant to trailing strings following it does not have any.
     * Trailing spaces will be automatically removed by the program.
     * @param command String representation of a single-word command.
     * @param taskString Description of a task, following a single-word command.
     * @throws DukeException If taskString taken in is non-empty.
     */
    public static void ensureEmptyTaskString(String command, String taskString) throws DukeException {
        if (!taskString.equals("")) {
            throw new DukeException("There cannot be any additional characters after the \"" + command + "\" command"
                    + " (other than trailing spaces).");
        }
    }

    /**
     * Ensures a command or task requiring trailing string(s) following it has it / them.
     * @param task Task or single-word command that requires a following description.
     * @param taskString Description of a task, following a single-word command.
     * @throws DukeException If taskString taken in is empty.
     */
    public static void ensureNonEmptyTaskString(String task, String taskString) throws DukeException {
        if (taskString.equals("")) {
            if (task.equals("delete") || task.equals("done")) {
                throw new DukeException("The index for a " + task + " command cannot be empty.");
            } else if (task.charAt(0) == 'a' || task.charAt(0) == 'e' || task.charAt(0) == 'i' ||
                    task.charAt(0) == 'o' || task.charAt(0) == 'u') {
                throw new DukeException("The description of an " + task + " cannot be empty.");
            } else {
                throw new DukeException("The description of a " + task + " cannot be empty.");
            }
        }
    }

    /**
     * Ensures that a user does not enter an empty line of command.
     * @param command String representation of a single-word command to the program.
     * @throws DukeException If user inputs an empty line of command.
     */
    public static void ensureNonEmptyCommand(String command) throws DukeException {
        if (command.equals("")) {
            throw new DukeException("Command cannot be empty.");
        }
    }

    /**
     * Returns a validated Date object.
     * Ensures that input String is in valid DD/MM/YYYY format.
     * Ensures there are at most 12 months in a year.
     * Ensures the day of the month in the year entered is valid.
     * @param date String representation of a date in DD/MM/YYYY format.
     * @return Validated Date object containing the day, month, year of the date parameter.
     * @throws DukeException If the date entered is invalid.
     */
    public static Date getValidatedDate(String date) throws DukeException {
        if (date.length() != 10) {
            throw new DukeException("Please ensure date format is in DD/MM/YYYY");
        }

        String[] dateArr = date.split("/");
        if (dateArr.length != 3) {
            throw new DukeException("Please ensure date format is in DD/MM/YYYY");
        }

        int day = 0;
        int month = 0;
        int year = 0;
        boolean isLeapYear = false;

        try {
            day = Integer.parseInt(dateArr[0]);
            month = Integer.parseInt(dateArr[1]);
            year = Integer.parseInt(dateArr[2]);
            if (day < 1) {
                throw new DukeException("Calendar day must start from 1");
            } else if (month < 1) {
                throw new DukeException("Calendar month must start from 1");
            } else if (year < 0) {
                throw new DukeException("Calendar year must start from 0");
            } else if (month > 12) {
                throw new DukeException("There cannot be more than 12 months in a year.");
            }
        } catch (NumberFormatException ex) {
            throw new DukeException("Please ensure date format is in DD/MM/YYYY");
        }

        if (year % 4 == 0) {
            isLeapYear = true;
        } else if (year % 100 == 0 && year % 400 == 0) {
            isLeapYear = true;
        }

        if ((month <= 7 && month % 2 != 0) || (month > 7 && month % 2 == 0)) {
            if (day > 31) {
                throw new DukeException("There cannot be more than 31 days in " + Date.getMonth(month));
            }
        } else if (month != 2) {
            if (day > 30) {
                throw new DukeException("There cannot be more than 30 days in " + Date.getMonth(month));
            }
        } else {
            if (isLeapYear && day > 29) {
                throw new DukeException("There cannot be more than 29 days in " + Date.getMonth(month) + " "
                        + year + ".");
            } else if (!isLeapYear && day > 28) {
                throw new DukeException("There cannot be more than 28 days in " + Date.getMonth(month) + " "
                        + year + ".");
            }
        }

        return new Date(day, month, year);
    }

    /**
     * Returns a validated Time object.
     * Ensures that input String is in valid HHMM format.
     * Ensures hours is not more than 23 and minutes is not more than 59.
     * @param time String representation of a time in HHMM format.
     * @return Validated Time object containing the day, month, year of the time parameter.
     * @throws DukeException If the time entered is invalid.
     */
    public static Time getValidatedTime(String time) throws DukeException {
        if (time.length() != 4) {
            throw new DukeException("Please ensure time format is in HHMM format (24 hours).");
        }

        int hour = 0;
        int minute = 0;

        try {
            hour = Integer.parseInt(time.substring(0, 2));
            minute = Integer.parseInt(time.substring(2, 4));
            if (hour < 0 || minute < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            throw new DukeException("Please ensure time format is in HHMM format (24 hours).");
        }

        if (hour > 23) {
            throw new DukeException("Please check that hours is not more than 23.");
        } else if (minute > 59) {
            throw new DukeException("Please check that minutes is not more than 59.");
        }
        return new Time(hour, minute);
    }

    /**
     * Returns a validated index for the given list.
     * Takes in a string and ensures that it is a valid positive integer within the range of the given TaskList 
     * (numbered from 1 to n in a TaskList of size n).
     * Ensures that any n that is returned will be able to access the TaskList without encountering a general
     * number format exception that will cause the program to terminate in the event that the index is invalid or
     * out of bounds.
     * Post-condition: A client of this method may use the returned value to access tasks in the TaskList 
     * if the String representing the index of the item in the taskList is valid.
     * @param tasks TaskList of which the validated index is to be returned.
     * @param k String that represents the ith element of the TaskList (k ranges from 1 to n).
     * @return An integer value i that can be used to access the item in the taskList (i ranges from 0 to n - 1).
     * @throws DukeException If k is less than 0, more than the size of the list or cannot be parsed as an integer.
     */
    public static int getValidatedListIndex(TaskList tasks, String k) throws DukeException {
        if (k.equals("")) {
            throw new DukeException("Please enter a valid numerical value after the intended command"
                    + " (separated by a space).\n" + "The number cannot be empty for this command.");
        }

        int n = 0;

        try {
            n = Integer.parseInt(k);
            if (n <= 0 || n > tasks.size()) {
                throw new NumberFormatException();
            }
            return n - 1;
        } catch (NumberFormatException ex) {
            if (tasks.size() == 1) {
                throw new DukeException("Please enter a valid numerical value from 1 to the taskList's size"
                        + " after the intended command (separated by a space).\n"
                        + "There is currently 1 task in the list.\n"
                        + "Try entering \"list\" to view the full list of tasks.");
            } else {
                throw new DukeException("Please enter a valid numerical value from 1 to the taskList's size"
                        + " after the intended command (separated by a space).\n"
                        + "There are currently " + tasks.size() + " tasks in the list.\n"
                        + "Try entering \"list\" to view the full list of tasks.");
            }
        }
    }
}
