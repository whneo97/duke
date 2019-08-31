package seedu.duke.task;

import seedu.duke.dateandtime.DateAndTime;

/**
 * Defines an Event object that represents a event task.
 * Inherits attributes and methods from Task class.
 */
public class Event extends Task {

    /**
     * Creates an Event object with a task description, along with date and time of Event.
     * @param taskString Task description of the event.
     * @param dateAndTime DateAndTime object representing the date and time during which the Event is occurring.
     */
    public Event(String taskString, DateAndTime dateAndTime) {
        super(Type.EVENT, taskString, dateAndTime);
    }

    /**
     * Creates a Deadline object with a task description, along with date and time of Event, and isDone state.
     * Allows a client of this client to define whether this Event has been completed.
     * @param taskString Task description of the Event.
     * @param dateAndTime DateAndTime object representing the date and time during which the the Event is occurring.
     * @param isDone A boolean that denotes whether or not the Event to be created has already been completed.
     */
    public Event(String taskString, DateAndTime dateAndTime, boolean isDone) {
        this(taskString, dateAndTime);
        this.setIsDone(isDone);
    }

    /**
     * Returns the String representation of an Event instance.
     * @return String representation of an Event instance, including the date, start and end time.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + this.getDateAndTime() + ")";
    }
}
