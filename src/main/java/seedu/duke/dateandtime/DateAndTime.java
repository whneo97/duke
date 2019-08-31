package duke.dateandtime;

public class DateAndTime {

    Date date;
    Time timeStart;
    Time timeEnd;

    public DateAndTime() {
    }

    public DateAndTime(Date date) {
        this.date = date;
    }

    public DateAndTime(Date date, Time timeEnd) {
        this(date);
        this.timeEnd = timeEnd;
    }

    public DateAndTime(Date date, Time timeStart, Time timeEnd) {
        this(date, timeEnd);
        this.timeStart = timeStart;
    }

    @Override
    public String toString() {
        if (timeEnd == null) {
            return "" + date;
        } else if (this.timeStart == null){
            return "" + date + " " + timeEnd;
        } else {
            return "" + date + " " + timeStart + "-" + timeEnd;
        }
    }
}
