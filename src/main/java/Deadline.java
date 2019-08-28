public class Deadline extends Task {

//    /**
//     * Constructor for an Deadline type class that takes in a task description.
//     * Deadline task that is created will automatically be added to the tasklist.
//     * @param taskString Deadline description for Deadline task.
//     * @throws DukeException Exception that is thrown in the case where the description is empty, or if the
//     *     description does not contain the appropriate separator " /by " that separates description and date/time of
//     *     the deadline.
//     */

    public Deadline(String taskString, DateAndTime dateAndTime) {
        super(Type.DEADLINE, taskString, dateAndTime);
    }

    public Deadline(String taskString, DateAndTime dateAndTime, boolean isDone) {
        this(taskString, dateAndTime);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dateAndTime + ")";
    }
}
