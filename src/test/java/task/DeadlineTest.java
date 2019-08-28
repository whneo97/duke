package task;

import duke.dateandtime.Date;
import duke.dateandtime.DateAndTime;
import duke.dateandtime.Time;
import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    Deadline dummyDeadline = new Deadline("dummyString",
            new DateAndTime(new Date(1, 3, 2014)));

    Deadline dummyDeadline2 = new Deadline("dummyString",
            new DateAndTime(new Date(2, 4, 2024), new Time(14, 4)), true);

    @Test
    public void dummyToString() {
        assertEquals("[D][ ] dummyString (by: 01/03/2014)", dummyDeadline.toString());
    }

    @Test
    public void dummyToString2() {
        assertEquals("[D][+] dummyString (by: 02/04/2024 1404)", dummyDeadline2.toString());
    }

}
