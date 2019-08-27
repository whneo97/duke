public class Deadline extends Task {

    /**
     * Constructor for an Deadline type class that takes in a task description.
     * Deadline task that is created will automatically be added to the tasklist.
     * @param taskString Deadline description for Deadline task.
     * @throws DukeException Exception that is thrown in the case where the description is empty, or if the
     *     description does not contain the appropriate separator " /by " that separates description and date/time of
     *     the deadline.
     */
    public Deadline(String taskString) throws DukeException {
        super("[D]");

        if (taskString.equals("") || taskString.equals("/by")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!taskString.contains("/by ")) {
            throw new DukeException("Please separate deadline description and date/time by \" /by \". \n" +
                    "Note that date/time of a deadline cannot be empty.");
        }

        String[] arr = taskString.split(" /by ");
        if (arr.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        this.taskString = arr[0];
        this.dateAndTime = arr[1];

        Task.tasklist.add(this);

        this.printAdded();
    }

    public Deadline() {
        super("[D]");
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dateAndTime + ")";
    }
}
