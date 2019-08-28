package task;

import duke.dateandtime.Date;
import duke.dateandtime.DateAndTime;
import duke.dateandtime.Time;
import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    Event dummyEvent = new Event("dummyString",
            new DateAndTime(new Date(7, 5, 2031), new Time(12, 32), new Time(15, 13)));

    Event dummyEvent2 = new Event("dummyString",
            new DateAndTime(new Date(3, 12, 2064),
                    new Time(12, 34), new Time(23, 54)), true);

    @Test
    public void dummyToString() {
        assertEquals("[E][ ] dummyString (at: 07/05/2031 1232-1513)", dummyEvent.toString());
    }

    @Test
    public void dummyToString2() {
        assertEquals("[E][+] dummyString (at: 03/12/2064 1234-2354)", dummyEvent2.toString());
    }

}
