public class Deadline extends Task {

    /**
     * Constructor for an Deadline type class that takes in a task description.
     * Deadline task that is created will automatically be added to the tasklist.
     * @param taskString Deadline description for Deadline task.
     * @throws DukeException Exception that is thrown in the case where the description is empty, or if the
     *     description does not contain the appropriate separator " /by " that separates description and date/time of
     *     the deadline.
     */
    public Deadline(String taskString) throws DukeException {
        super("[D]");

        if (taskString.equals("") || taskString.equals("/by")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!taskString.contains("/by ")) {
            throw new DukeException("Please separate deadline description and date/time by \" /by \". \n" +
                    "Note that date/time of a deadline cannot be empty.");
        }

        String[] arr = taskString.split(" /by ");
        if (arr.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        this.taskString = arr[0];

        int[] dateTimeArr = getValidatedDateAndTime(arr[1]);
        this.day = dateTimeArr[0];
        this.month = dateTimeArr[1];
        this.year = dateTimeArr[2];
        this.hour = dateTimeArr[3];
        this.minute = dateTimeArr[4];
        Task.tasklist.add(this);

        this.printAdded();
    }

    public Deadline() {
        super("[D]");
    }

    public static int[] getValidatedDateAndTime(String s) throws DukeException {
        String[] dateTimeArr = s.split(" ");
        String date = dateTimeArr[0];
        String time = dateTimeArr.length > 1 ? dateTimeArr[1]: "";
        if (date.length() != 10 || (!time.equals("") && time.length() != 4)) {
            throw new DukeException("Please ensure date/time format is either in 'DD/MM/YYYY' or 'DD/MM/YYYY HHMM'\n" +
                    "(24 hr clock) format");
        }

        String[] dateArr = date.split("/");
        if (dateArr.length != 3) {
            throw new DukeException("Please ensure date/time format is either in 'DD/MM/YYYY' or 'DD/MM/YYYY HHMM'\n" +
                    "(24 hr clock) format");
        }

        int day = 0;
        int month = 0;
        int year = 0;
        boolean isLeapYear = false;

        try {
            day = Integer.parseInt(dateArr[0]);
            month = Integer.parseInt(dateArr[1]);
            year = Integer.parseInt(dateArr[2]);
            if (day < 1 || month < 1 || year < 0) {
                throw new NumberFormatException();
            } else if (month > 12) {
                throw new DukeException("There cannot be more than 12 months in a year.");
            }
        } catch (NumberFormatException ex) {
            throw new DukeException("Please ensure date/time format is either in 'DD/MM/YYYY' or 'DD/MM/YYYY HHMM'\n" +
                    "(24 hr clock) format");
        }

        if (year % 4 == 0) {
            isLeapYear = true;
        } else if (year % 100 == 0 && year % 400 == 0) {
            isLeapYear = true;
        }

        if ((month <= 7 && month % 2 != 0) || (month > 7 && month % 2 == 0)) {
            if (day > 31) {
                throw new DukeException("There cannot be more than 31 days in this month.");
            }
        } else if (month != 2) {
            if (day > 30) {
                throw new DukeException("There cannot be more than 30 days in this month.");
            }
        } else {
            if (isLeapYear && day > 29) {
                throw new DukeException("There cannot be more than 29 days in this month and year.");
            } else if (!isLeapYear && day > 28) {
                throw new DukeException("There cannot be more than 28 days in this month and year.");
            }
        }

        int hour = -1;
        int minute = -1;

        if (!time.equals("")) {

            try {
                hour = Integer.parseInt(time.substring(0, 2));
                minute = Integer.parseInt(time.substring(2, 4));
                if (hour < 0 || minute < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                throw new DukeException("Please ensure date/time format is either in 'DD/MM/YYYY' or 'DD/MM/YYYY HHMM'\n" +
                        "(24 hr clock) format");
            }

            if (hour > 23 || minute > 59) {
                throw new DukeException("Please check that hours is not more than 23 and minutes is not more than 59");
            }
        }

        return new int[]{day, month, year, hour, minute};
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getDateAndTime() + ")";
    }
}
