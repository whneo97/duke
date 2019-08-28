public class DeadlineValidation extends Validation {
    public static String[] getValidatedDescriptionAndDateTime(String taskString) throws DukeException {
        if (!taskString.contains("/by ")) {
            throw new DukeException("Please separate deadline description and date/time by \" /by \". \n" +
                    "Note that date/time of a deadline cannot be empty.");
        }

        String[] arr = taskString.split(" /by ");
        if (arr.length < 2) {
            throw new DukeException("The description of an deadline cannot be empty.");
        }
        taskString = arr[0];

        return new String[]{arr[0], arr[1]};
    }
    
    public static DateAndTime getValidatedDateAndTime(String s) throws DukeException {
        String[] dateTimeArr = s.split(" ");
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
