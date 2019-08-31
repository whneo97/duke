package duke.dateandtime;

public class Time {
    int hour;
    int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString() {
        return String.format("%02d", hour) + String.format("%02d", minute);
    }
}
