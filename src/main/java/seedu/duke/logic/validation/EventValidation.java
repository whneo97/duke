package seedu.duke.logic.validation;

import seedu.duke.model.dateandtime.Date;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.model.dateandtime.Time;
import seedu.duke.commons.exceptions.DukeException;

/**
 * Defines an EventValidation class containing static methods.
 * Validates strings parsed to create an Event.
 * Inherits methods from Validation class.
 */
public class EventValidation extends Validation {

    /**
     * Returns a String array of two elements containing task description, date, start and end time respectively.
     * @param descriptionAndDateTimeString String in the form [task description] [" /at "] [start time] ["-"]
     *                                     ["end time].
     * @return String array of two elements containing task description, date, start and end time respectively.
     * @throws DukeException If the format of the String entered is invalid.
     */

    public static String[] getValidatedDescriptionAndDateTime(String descriptionAndDateTimeString)
            throws DukeException {
        if (!descriptionAndDateTimeString.contains("/at ")) {
            throw new DukeException("Please separate deadline description and date/time by \" /at \". \n"
                    + "Note that date/time of an event cannot be empty.");
        }

        String[] arr = descriptionAndDateTimeString.split(" /at ");
        
        if (arr.length < 2) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        return new String[]{arr[0], arr[1]};
    }

    /**
     * Returns a String array of three elements containing date, start time and end time respectively.
     * @param dateAndTimeString String in the form of [date] [start time] [end time].
     * @return String array of three elements containing date, start time and end time respectively.
     * @throws DukeException If the format of the String entered is invalid.
     */
    public static DateAndTime getValidatedDateAndTime(String dateAndTimeString) throws DukeException {
        String[] dateTimeArr = dateAndTimeString.split(" ");
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
