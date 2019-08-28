package duke.task;

import duke.dateandtime.DateAndTime;

import java.util.ArrayList;

public class Task {

    public enum Type {
        TODO, DEADLINE, EVENT;
    }

    String taskString;
    boolean isDone = false;
    Type type;
    DateAndTime dateAndTime;

    /**
     * Constructor for Task that takes in the type of task intended to be created.
     * Precondition: Task type is a valid type that has its own individual class.
     * Type will be assigned to the class' type attribute.
     * Post-condition: Printing and adding the task to taskList is to be handled by individual classes of each type.
     * (i.e. client that calls this constructor manually adds the task into the taskList)
     * @param type Type of task (either todo, deadline or event)
     */
    //
    public Task(Type type, String taskString, DateAndTime dateAndTime) {
        this.type = type;
        this.taskString = taskString;
        this.dateAndTime = dateAndTime;
    }

    /**
     * Pre-condition: The Task that uses this method has been added to the taskList.
     * Prints the statement to the user that the particular task has been added into the taskList
     */
    public String addedMessage(TaskList tasks) {
        String s = "Got it. I've added this task:\n  " + this + "\n" +
                "Now you have " + tasks.size();
        if (tasks.size() == 1) {
            s += " task in the list";
        }  else {
            s += " tasks in the list";
        }
        return s;
    }

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

    public String doneMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    public String getTaskString() {
        return taskString;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public Type getType() {
        return type;
    }

    public DateAndTime getDateAndTime() {
        return dateAndTime;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setTaskString(String taskString) {
        this.taskString = taskString;
    }

    public void setDateAndTime(DateAndTime dateAndTime) { this.dateAndTime = dateAndTime; }

    @Override
    public String toString() {
        String doneString = isDone == true ? "[+]" : "[ ]";
        return "[" + type.toString().charAt(0) + "]" + doneString + " " + this.taskString;
    }
}
