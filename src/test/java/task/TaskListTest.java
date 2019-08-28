package task;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    Task dummyTask = new Todo("dummyTaskString");
    TaskList dummytasks = new TaskList();

    @Test
    public void dummySize() {
        assertEquals(0, dummytasks.size());
    }
}
