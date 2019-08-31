package duke.task;

public class Todo extends Task {

    public Todo(String taskString) {
        super(Type.TODO, taskString, null);
    }

    public Todo(String taskString, boolean isDone) {
        this(taskString);
        this.isDone = isDone;
    }
}
