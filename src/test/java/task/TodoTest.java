package task;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    Todo dummyTodo = new Todo("dummyString");

    Todo dummyTodo2 = new Todo("dummyString2", true);

    @Test
    public void dummyToString() {
        assertEquals("[T][ ] dummyString", dummyTodo.toString());
    }

    @Test
    public void dummyToString2() {
        assertEquals("[T][+] dummyString2", dummyTodo2.toString());
    }

}
