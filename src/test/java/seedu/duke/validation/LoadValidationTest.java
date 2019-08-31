package seedu.duke.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadValidationTest {

    @Test
    public void dummyGetValidatedTaskType() throws DukeException {
        Assertions.assertEquals("T", LoadValidation.getValidatedTaskType("T"));
    }

    @Test
    public void dummyGetValidatedDoneStatus() throws DukeException {
        assertEquals(1, LoadValidation.getValidatedDoneStatus("1"));
    }

}
