package seedu.duke.dateandtime;

/**
 * Defines a Date object containing a day, month and year attribute.
 */
public class Date {
    private int day;
    private int month;
    private int year;

    /**
     * Creates an instance of Date.
     * @param day Day of the month starting from 1 to the last day of the month.
     * @param month Numerical representation of a month from 1 to 12.
     * @param year Year beginning from 0 AD.
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Returns the String representation of the month from its numerical representation.
     * @param month Numerical representation of a month from 1 to 12.
     * @return String representation of the month (eg. January) converted from its numerical representation.
     */
    public static String getMonth(int month) {
        return month == 1
                ? "January"
                : month == 2
                ? "February"
                : month == 3
                ? "March"
                : month == 4
                ? "April"
                : month == 5
                ? "May"
                : month == 6
                ? "June"
                : month == 7
                ? "July"
                : month == 8
                ? "August"
                : month == 9
                ? "September"
                : month == 10
                ? "October"
                : month == 11
                ? "November"
                : month == 12
                ? "December"
                : "";
    }

    /**
     * Returns the String representation of a Date instance.
     * @return String representation of a Date instance in DD/MM/YYYY format.
     */
    public String toString() {
        return String.format("%02d", day)
                + "/" + String.format("%02d", month)
                + "/" + String.format("%02d", year);
    }

}
