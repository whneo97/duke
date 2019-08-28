package duke.dateandtime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateAndTimeTest {

    Date date = new Date(1, 4, 2018);
    Time time = new Time(17, 42);

    @Test
    public void dummyToString() {
        assertEquals("01/04/2018 1742", new DateAndTime(date, time).toString());
    }

}
