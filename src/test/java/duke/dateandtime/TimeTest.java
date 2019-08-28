package duke.dateandtime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {

    @Test
    public void dummyToString() {
        assertEquals("2359", new Time(23, 59).toString());
    }

}
