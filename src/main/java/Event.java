public class Event extends Task {

    /**
     * Constructor for an Event type class that takes in a task description.
     * Event task that is created will automatically be added to the tasklist.
     * @param taskString Event description for Event task.
     * @throws DukeException Exception that is thrown in the case where the description is empty, or if the
     *     description does not contain the appropriate separator " /at " that separates description and date/time of
     *     the event.
     */
    public Event(String taskString) throws DukeException {
        super("[E]");

        if (taskString.equals("")) {
            throw new DukeException("The description of an event cannot be empty.");
        } else if (!taskString.contains("/at ")) {
            throw new DukeException("Please separate event description and date/time by \" /at \". \n" +
                    "Note that date/time of an event cannot be empty.");
        }

        String[] arr = taskString.split(" /at ");
        if (arr.length < 2) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        this.taskString = arr[0];

        int[] dateTimeArr = getValidatedDateAndTime(arr[1]);
        this.day = dateTimeArr[0];
        this.month = dateTimeArr[1];
        this.year = dateTimeArr[2];
        this.hour = dateTimeArr[3];
        this.minute = dateTimeArr[4];
        this.hourEnd = dateTimeArr[5];
        this.minuteEnd = dateTimeArr[6];

        Task.tasklist.add(this);

        this.printAdded();
    }

    public Event() {
        super("[E]");
    }

    public static int[] getValidatedDateAndTime(String s) throws DukeException {
        String[] dateTimeArr = s.split(" ");
        if (dateTimeArr.length < 2) {
            throw new DukeException("Please ensure date/time format is in DD/MM/YYYY HHMM-HHMM format (24 hours).");
        }
        String date = dateTimeArr[0];
        String[] timeArr = dateTimeArr[1].split("-");
        if (timeArr.length < 2) {
            throw new DukeException("Please ensure date/time format is in DD/MM/YYYY HHMM-HHMM format (24 hours).");
        }
        String timeStart = timeArr[0];
        String timeEnd = timeArr[1];
        if (date.length() != 10 || timeStart.length() != 4 || timeEnd.length() != 4) {
            throw new DukeException("Please ensure date/time format is in DD/MM/YYYY HHMM-HHMM format (24 hours).");
        }

        String[] dateArr = date.split("/");
        if (dateArr.length != 3) {
            throw new DukeException("Please ensure date/time format is in DD/MM/YYYY HHMM-HHMM format (24 hours).");
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
            throw new DukeException("Please ensure date/time format is in DD/MM/YYYY HHMM-HHMM format (24 hours).");
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

        int hour1 = 0;
        int minute1 = 0;
        int hour2 = 0;
        int minute2 = 0;

        try {
            hour1 = Integer.parseInt(timeStart.substring(0, 2));
            minute1 = Integer.parseInt(timeStart.substring(2, 4));
            hour2 = Integer.parseInt(timeEnd.substring(0, 2));
            minute2 = Integer.parseInt(timeEnd.substring(2, 4));
            if (hour1 < 0 || minute1 < 0 || hour2 < 0 || minute2 < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            throw new DukeException("Please ensure date/time format is in DD/MM/YYYY HHMM-HHMM format (24 hours).");
        }

        if (hour1 > 23 || hour2 > 23 || minute1 > 59 || minute2 > 59) {
            throw new DukeException("Please check that hours is not more than 23 and minutes is not more than 59");
        }

        return new int[]{day, month, year, hour1, minute1, hour2, minute2};
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.getDateAndTime() + ")";
    }
}
