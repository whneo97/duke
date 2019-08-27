public class Todo extends Task {

    /**
     * Constructor for a Todo type class that takes in a task description.
     * Todo task that is created will automatically be added to the tasklist.
     * @param taskString Task description for Todo task.
     * @throws DukeException Exception that is thrown in the case where the description is empty.
     */
    public Todo(String taskString) throws DukeException {
        super("[T]");
        if (taskString.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Task.tasklist.add(this);

        this.taskString = taskString;
        this.printAdded();
    }

    public Todo() {
        super("[T]");
    }
}
