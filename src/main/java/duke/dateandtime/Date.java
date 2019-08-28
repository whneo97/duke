package duke.dateandtime;

public class Date {
    int day;
    int month;
    int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static String getMonth(int month) {
        return month == 1 ? "January"
                : month == 2 ? "February"
                : month == 3 ? "March"
                : month == 4 ? "April"
                : month == 5 ? "May"
                : month == 6 ? "June"
                : month == 7 ? "July"
                : month == 8 ? "August"
                : month == 9 ? "September"
                : month == 10 ? "October"
                : month == 11 ? "November"
                : month == 12 ? "December"
                : "";
    }

    public String toString() {
        return String.format("%02d", day)
                + "/" + String.format("%02d", month)
                + "/" + String.format("%02d", year);
    }

}
