package seedu.duke.model.task;

/**
 * Defines a Todo object that represents a todo task.
 * Inherits attributes and methods from Task class.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object with a task description.
     * @param taskString Task description of the todo.
     */
    public Todo(String taskString) {
        super(Type.TODO, taskString, null);
    }

    /**
     * Creates a Todo object with a task description and isDone state.
     * Allows a client of this client to define whether this Todo has been completed.
     * @param taskString Task description of the Todo.
     * @param isDone A boolean that denotes whether or not the Todo to be created has already been completed.
     */
    public Todo(String taskString, boolean isDone) {
        this(taskString);
        this.setIsDone(isDone);
    }
}
