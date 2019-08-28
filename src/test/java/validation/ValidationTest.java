package validation;

import duke.dateandtime.Date;
import duke.dateandtime.Time;
import duke.exceptions.DukeException;
import duke.validation.Validation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationTest {

    @Test
    public void dummyGetValidatedDate() throws DukeException {
        assertEquals((new Date(13, 9, 2018)).toString(),
                Validation.getValidatedDate("13/09/2018").toString());
    }

    @Test
    public void dummyGetValidatedTime() throws DukeException {
        assertEquals((new Time(11, 55)).toString(),
                Validation.getValidatedTime("1155").toString());
    }
}
