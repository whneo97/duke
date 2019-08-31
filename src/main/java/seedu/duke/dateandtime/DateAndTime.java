package seedu.duke.dateandtime;

/**
 * Defines a DateAndTime object.
 * Consists of different combination of Date and Time objects.
 */
public class DateAndTime {

    private Date date;
    private Time timeStart;
    private Time timeEnd;

    /**
     * Creates an instance of DateAndTime in [Date] format.
     * Stores a Date object as it's attribute.
     * @param date Date object representing the date stored by an instance of this class.
     */
    public DateAndTime(Date date) {
        this.date = date;
    }

    /**
     * Creates an instance of DateAndTime in [Date] [Time] format.
     * Stores a Date and Time object (ending time) as it's attributes.
     * @param date Date object representing the date stored by an instance of this class.
     * @param timeEnd Time object representing the ending time stored by an instance of this class.
     */
    public DateAndTime(Date date, Time timeEnd) {
        this(date);
        this.timeEnd = timeEnd;
    }

    /**
     * Creates an instance of DateAndTime in [Date] [Time] [Time] format.
     * Stores a Date and two Time objects (both starting and ending time) as it's attributes.
     * @param date Date object representing the date stored by an instance of this class.
     * @param timeStart Time object representing the starting time stored by an instance of this class.
     * @param timeEnd Time object representing the ending time stored by an instance of this class.
     */
    public DateAndTime(Date date, Time timeStart, Time timeEnd) {
        this(date, timeEnd);
        this.timeStart = timeStart;
    }

    /**
     * Returns the String representation of a DateAndTime instance.
     * @return String representation of a DateAndTime instance, with Time followed by Date.
     */
    @Override
    public String toString() {
        if (timeEnd == null) {
            return "" + date;
        } else if (this.timeStart == null) {
            return "" + date + " " + timeEnd;
        } else {
            return "" + date + " " + timeStart + "-" + timeEnd;
        }
    }
}
