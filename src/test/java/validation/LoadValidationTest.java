package validation;

import duke.exceptions.DukeException;
import duke.validation.LoadValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadValidationTest {

    @Test
    public void dummyGetValidatedTaskType() throws DukeException {
        assertEquals("T", LoadValidation.getValidatedTaskType("T"));
    }

    @Test
    public void dummyGetValidatedDoneStatus() throws DukeException {
        assertEquals(1, LoadValidation.getValidatedDoneStatus("1"));
    }

}
