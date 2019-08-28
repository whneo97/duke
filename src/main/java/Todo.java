public class Todo extends Task {
    /**
     * Constructor for a Todo type class that takes in a task description.
     * Todo task that is created will automatically be added to the tasklist.
     * @param taskString Task description for Todo task.
     * @throws DukeException Exception that is thrown in the case where the description is empty.
     */

    public Todo(String taskString) {
        super(Type.TODO, taskString, null);
    }

    public Todo(String taskString, boolean isDone) {
        this(taskString);
        this.isDone = isDone;
    }
}
