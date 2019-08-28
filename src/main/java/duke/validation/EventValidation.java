package duke.validation;

import duke.dateandtime.Date;
import duke.dateandtime.DateAndTime;
import duke.dateandtime.Time;
import duke.exceptions.DukeException;

public class EventValidation extends Validation {

    public static String[] getValidatedDescriptionAndDateTime(String taskString) throws DukeException {
        if (!taskString.contains("/at ")) {
            throw new DukeException("Please separate deadline description and date/time by \" /at \". \n" +
                    "Note that date/time of an event cannot be empty.");
        }

        String[] arr = taskString.split(" /at ");
        if (arr.length < 2) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        taskString = arr[0];
        return new String[]{arr[0], arr[1]};
    }

    public static DateAndTime getValidatedDateAndTime(String s) throws DukeException {
        String[] dateTimeArr = s.split(" ");
        if (dateTimeArr.length != 2) {
            throw new DukeException("Please ensure date format is in DD/MM/YYYY HHMM-HHMM format (24 hours).");
        }
        Date date = getValidatedDate(dateTimeArr[0]);
        String[] timeArr = dateTimeArr[1].split("-");
        if (timeArr.length != 2) {
            throw new DukeException("Please ensure time format is in HHMM-HHMM format (24 hours).");
        }
        Time timeStart = getValidatedTime(timeArr[0]);
        Time timeEnd = getValidatedTime(timeArr[1]);

        return new DateAndTime(date, timeStart, timeEnd);
    }
}
