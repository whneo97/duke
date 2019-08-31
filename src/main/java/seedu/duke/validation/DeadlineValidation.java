package seedu.duke.validation;

import seedu.duke.dateandtime.Date;
import seedu.duke.dateandtime.DateAndTime;
import seedu.duke.dateandtime.Time;
import seedu.duke.exceptions.DukeException;

/**
 * Defines a DeadlineValidation class containing static methods.
 * Validates Strings parsed to create a Deadline.
 * Inherits methods from Validation class.
 */
public class DeadlineValidation extends Validation {

    /**
     * Returns a String array of two elements containing task description and date / time respectively.
     * @param descriptionAndDateTimeString String in the form [task description] [" /by "] [date and optional time].
     * @return String array of two elements containing task description and date / time respectively.
     * @throws DukeException If the format of the String entered is invalid.
     */
    public static String[] getValidatedDescriptionAndDateTime(String descriptionAndDateTimeString)
            throws DukeException {

        if (!descriptionAndDateTimeString.contains("/by ")) {
            throw new DukeException("Please separate deadline description and date/time by \" /by \". \n"
                    + "Note that date/time of a deadline cannot be empty.");
        }

        String[] arr = descriptionAndDateTimeString.split(" /by ");
        
        if (arr.length < 2) {
            throw new DukeException("The description of an deadline cannot be empty.");
        }
        
        return new String[]{arr[0], arr[1]};
    }

    /**
     * Returns a String array of two elements containing date and time respectively.
     * @param dateAndTimeString String in the form of either [date] or [date] [time].
     * @return String array of two elements containing task description and date / time respectively.
     * @throws DukeException If the format of the String entered is invalid.
     */
    public static DateAndTime getValidatedDateAndTime(String dateAndTimeString) throws DukeException {
        String[] dateTimeArr = dateAndTimeString.split(" ");
        if (dateTimeArr.length > 2) {
            throw new DukeException("Please ensure date format is either in DD/MM/YYYY format or " 
                    + "DD/MM/YYYY HHMM format (24 hours).");
        }
        Date date = getValidatedDate(dateTimeArr[0]);
        Time time = dateTimeArr.length > 1 ? getValidatedTime(dateTimeArr[1]) : null;

        if (time != null) {
            return new DateAndTime(date, time);
        } else {
            return new DateAndTime(date);
        }
    }
}
