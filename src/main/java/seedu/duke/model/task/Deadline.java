package seedu.duke.model.task;

import seedu.duke.model.dateandtime.DateAndTime;

/**
 * Defines a Deadline object that represents a deadline task.
 * Inherits attributes and methods from Task class.
 */
public class Deadline extends Task {

    /**
     * Creates a Deadline object with a task description and due date (and possibly time).
     * @param taskString Task description of the deadline.
     * @param dateAndTime DateAndTime object representing the date (and possibly time) which the task
     *                    with given deadline is due by.
     */
    public Deadline(String taskString, DateAndTime dateAndTime) {
        super(Type.DEADLINE, taskString, dateAndTime);
    }

    /**
     * Creates a Deadline object with a task description, due date (and possibly time) and isDone state.
     * Allows a client of this client to define whether this Deadline has been completed.
     * @param taskString Task description of the Deadline.
     * @param dateAndTime DateAndTime object representing the date (and possibly time) which the task
     *                    with given deadline is due by.
     * @param isDone A boolean that denotes whether or not the Deadline to be created has already been completed.
     */
    public Deadline(String taskString, DateAndTime dateAndTime, boolean isDone) {
        this(taskString, dateAndTime);
        this.setIsDone(isDone);
    }

    /**
     * Returns the String representation of a Deadline instance.
     * @return String representation of a Deadline instance, including the date (and possibly time).
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getDateAndTime() + ")";
    }
}
