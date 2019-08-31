package seedu.duke.dateandtime;

/**
 * Defines a Time object containing hour and minute as attributes.
 *
 */
public class Time {
    private int hour;
    private int minute;

    /**
     * Creates an instance of Time.
     * @param hour Hour component of the 24 hour clock.
     * @param minute Minute component of the 24 hour clock.
     */
    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Returns the String representation of a Time instance.
     * @return String representation of a Time instance in HHMM format.
     */
    @Override
    public String toString() {
        return String.format("%02d", hour) + String.format("%02d", minute);
    }
}
