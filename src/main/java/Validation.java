import java.util.ArrayList;

public class Validation {

    /**
     * Method that ensures a command that is not meant to have additional strings following behind it does not have
     * additional strings following it (eg. list 7)
     * @param taskString Description of a task, following a command
     * @throws DukeException Exception that is thrown if taskString taken in is non-empty
     */

    public static void ensureEmptyTaskString(String command, String taskString) throws DukeException {
        if (!taskString.equals("")) {
            throw new DukeException("There cannot be any additional characters after the \"" + command + "\" command"
                    + " (other than trailing spaces).");
        }
    }

    public static void ensureNonEmptyCommand(String command) throws DukeException {
        if (command.equals("")) {
            throw new DukeException("Command cannot be empty.");
        }
    }

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
                throw new DukeException("There cannot be more than 29 days in " + Date.getMonth(month) + " " + year);
            } else if (!isLeapYear && day > 28) {
                throw new DukeException("There cannot be more than 28 days in " + Date.getMonth(month) + " " + year);
            }
        }

        return new Date(day, month, year);
    }

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
     * Takes in a string and ensures that it is a valid positive integer that is within the range of the taskList
     * (numbered from 0 to n - 1 if there are items in the taskList, where n is the size of the taskList).
     * Post-condition: A client of this method may use it to access tasks in the taskList if the string representing
     * the index of the item in the taskList is valid, or otherwise user will be prompted to re-enter the command and
     * number.
     * @param s String that represents index of item in taskList.
     * @return An int that is to be used to access the item in the taskList. This ensures that any n that is returned
     *     will be able to access the taskList and that the program can continue running without encountering a general
     *     number format exception that will cause the program to terminate in the event that the index is invalid or
     *     out of bounds
     * @throws DukeException Exception that is thrown if the numeric value of the string taken in does not correspond
     *     to a valid integer that can be used to access any task in the taskList. A valid number should start from 0
     *     and not equal or exceed the size of the current taskList.
     */
    public static int getValidatedListIndex(TaskList tasks, String s) throws DukeException {
        if (s.equals("")) {
            throw new DukeException("Please enter a valid numerical value after the intended command"
                    + " (separated by a space).\n" + "The number cannot be empty for this command.");
        }

        int n = 0;

        try {
            n = Integer.parseInt(s);
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
