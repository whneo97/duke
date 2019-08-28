public class Event extends Task {

//    /**
//     * Constructor for an Event type class that takes in a task description.
//     * Event task that is created will automatically be added to the tasklist.
//     * @param taskString Event description for Event task.
//     * @throws DukeException Exception that is thrown in the case where the description is empty, or if the
//     *     description does not contain the appropriate separator " /at " that separates description and date/time of
//     *     the event.
//     */

    public Event(String taskString, DateAndTime dateAndTime) {
        super("event", taskString, dateAndTime);
    }

    public Event(String taskString, DateAndTime dateAndTime, String isDone) {
        this(taskString, dateAndTime);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + dateAndTime + ")";
    }
}
