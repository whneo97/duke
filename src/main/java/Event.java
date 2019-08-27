public class Event extends Task {

    /**
     * Constructor for an Event type class that takes in a task description.
     * Event task that is created will automatically be added to the tasklist.
     * @param taskString Event description for Event task.
     * @throws DukeException Exception that is thrown in the case where the description is empty, or if the
     *     description does not contain the appropriate separator " /at " that separates description and date/time of
     *     the event.
     */
    public Event(String taskString) throws DukeException {
        super("[E]");

        if (taskString.equals("")) {
            throw new DukeException("The description of an event cannot be empty.");
        } else if (!taskString.contains("/at ")) {
            throw new DukeException("Please separate event description and date/time by \" /at \". \n" +
                    "Note that date/time of an event cannot be empty.");
        }

        String[] arr = taskString.split(" /at ");
        if (arr.length < 2) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        this.taskString = arr[0];
        this.dateAndTime = arr[1];

        Task.tasklist.add(this);

        this.printAdded();
    }

    public Event() {
        super("[E]");
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.dateAndTime + ")";
    }
}
