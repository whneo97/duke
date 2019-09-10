package seedu.duke.model.task;

import seedu.duke.model.dateandtime.DateAndTime;

/**
 * Defines a Task object that represents a task.
 */
public class Task {

    public enum Type {
        TODO, DEADLINE, EVENT;
    }

    private String taskString;
    private boolean isDone = false;
    private boolean markedAsDelete = false;
    private Type type;
    private DateAndTime dateAndTime;

    /**
     * Creates a Task object with a task description, type and date / time if any.
     * @param type Type of Task to be created.
     * @param taskString Task description of the task.
     * @param dateAndTime DateAndTime object representing the date and time associated with this task.
     *                    If there is no DateAndTime associated with this task, a client of this class may pass in
     *                    null for this parameter.
     */
    public Task(Type type, String taskString, DateAndTime dateAndTime) {
        this.type = type;
        this.taskString = taskString;
        this.dateAndTime = dateAndTime;
    }

    /**
     * Returns a String that indicates that this Task instance has been added to the given TaskList.
     * Indicates also the updated number of tasks in the given TaskList
     * Pre-condition: This Task has indeed been added to the given TaskList.
     * @param tasks TaskList to which this Task has been added.
     * @return String indicating that this Task instance has been added to the given TaskList.
     */
    public String addedMessage(TaskList tasks) {
        String s = "Got it. I've added this task:\n  " + this + "\n"
                + "Now you have " + tasks.size();
        if (tasks.size() == 1) {
            s += " task in the list.";
        }  else {
            s += " tasks in the list.";
        }
        assert tasks.size() > 0 : "Task is claimed to have been added to TaskList but TaskList contains less than "
                + "1 item.";
        return s;
    }

    /**
     * Returns a String that indicates that this Task instance has been removed from the given TaskList.
     * Indicates also the updated number of tasks remaining in the given TaskList
     * Pre-condition: This Task has indeed been removed to the given TaskList.
     * @param tasks TaskList to which this Task has been removed.
     * @return String indicating that this Task instance has been removed from the given TaskList.
     */
    public String deletedMessage(TaskList tasks) {
        String s = "Noted. I've removed this task:\n"
                + this + "\n";
        if (tasks.size() == 1) {
            s += "Now you have 1 task in the list.";
        } else {
            s += "Now you have " + tasks.size() + " tasks in the list.";
        }
        return s;
    }

    /**
     * Returns a String that indicates that this Task instance has been marked as done.
     * Pre-condition: This Task has indeed been marked as done.
     * @return String indicating that this Task instance has been marked as done.
     */
    public String doneMessage() {
        return "Nice! I've marked this task as done:\n  " + this;
    }

    /**
     * Returns the task description of this Task.
     * @return Task description of this Task stored in the taskString attribute.
     */
    public String getTaskString() {
        assert taskString != null : "Task does not have a task description";
        return taskString;
    }

    /**
     * Returns the state of whether the Task has been marked as done (completed).
     * @return A boolean stored in the isDone attribute denoting whether the Task has been marked as done (completed).
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the type of Task stored in this Task instance's type attribute.
     * Represented by Type constant.
     * @return Type of task stored in this Task instance's type attribute.
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns a DateAndTime object stored in this Task instance's dateAndTime attribute.
     * Returns null if this Task has no date and time associated with it.
     * @return DateAndTime object stored in this Task instance's dateAndTime attribute.
     */
    public DateAndTime getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Returns the boolean of whether this Task instance is to be marked as delete.
     * @return True or false boolean on whether this Task instance is to be marked as delete.
     */
    public boolean getMarkedAsDelete() {
        return markedAsDelete;
    }

    /**
     * Sets this instance of task to be marked as deleted.
     * Post-requisite: The Duke program will delete all tasks that have been marked to be deleted.
     */
    public void markAsDelete() {
        markedAsDelete = true;
    }

    /**
     * Sets the state of whether this Task has been completed.
     * @param isDone A boolean passed in by a client of this method to determine whether this Task has been marked as
     *               done.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
        assert this.isDone == isDone : "State of whether Task has been completed was intended to be set "
                + "but was not set by system.";
    }

    /**
     * Returns the String representation of a Task instance.
     * Formats the String to be returned such that it contains the Task's as type, state of whether it has been
     * completed, and task description in this order.
     * @return String representation of a Task instance.
     */
    @Override
    public String toString() {
        assert taskString != null : "Task does not have a task description";
        String doneString = isDone == true ? "[+]" : "[ ]";
        return "[" + type.toString().charAt(0) + "]" + doneString + " " + this.taskString;
    }
}
