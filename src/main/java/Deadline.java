class Deadline extends Task {

    /**
     * Constructor for an Deadline type class that takes in a task description.
     * Deadline task that is created will automatically be added to the tasklist.
     * @param taskString Deadline description for Deadline task.
     * @throws DukeException Exception that is thrown in the case where the description is empty, or if the
     *     description does not contain the appropriate separator " /by " that separates description and date/time of
     *     the deadline.
     */
    Deadline(String taskString) throws DukeException {
        super("[D]");

        if (taskString.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!taskString.contains("/by ")) {
            throw new DukeException("Please separate deadline description and date/time by \" /by \".");
        }

        Task.tasklist.add(this);

        String[] arr = taskString.split(" /by ");
        this.taskString = arr[0];
        this.dateAndTime = arr[1];
        this.printAdded();
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dateAndTime + ")";
    }
}
