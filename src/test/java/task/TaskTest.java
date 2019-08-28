package task;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    Task dummyTask = new Todo("dummyTaskString");
    TaskList dummytasks = new TaskList();

    @Test
    public void dummyAddedMessage() {
        String s = "Got it. I've added this task:\n  [T][ ] dummyTaskString\n" +
                "Now you have 0 tasks in the list.";
        assertEquals(s, dummyTask.addedMessage(dummytasks));
    }

    @Test
    public void dummyDeletedMessage() {
        String s = "Noted. I've removed this task:\n[T][ ] dummyTaskString\n" +
                "Now you have 0 tasks in the list.";
        assertEquals(s, dummyTask.deletedMessage(dummytasks));
    }

    @Test
    public void dummyDoneMessage() {
        String s = "Nice! I've marked this task as done:\n  [T][ ] dummyTaskString";
        assertEquals(s, dummyTask.doneMessage(dummyTask));
    }

    @Test
    public void dummyGetTaskString() {
        assertEquals("dummyTaskString", dummyTask.getTaskString());
    }

    @Test
    public void dummyGetIsDone() {
        assertEquals(false, dummyTask.getIsDone());
    }

    @Test
    public void dummyGetType() {
        assertEquals(Task.Type.TODO, dummyTask.getType());
    }

    @Test
    public void dummyGetDateAndTime() {
        assertEquals(null, dummyTask.getDateAndTime());
    }

    @Test
    public void dummyToString() {
        assertEquals("[T][ ] dummyTaskString", dummyTask.toString());
    }
}
