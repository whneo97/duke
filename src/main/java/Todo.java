class Todo extends Task{
    Todo(String taskString) throws DukeException {
        super("[T]");
        if (taskString.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Task.tasklist.add(this);

        this.taskString = taskString;
        this.printAdded();
    }
}
