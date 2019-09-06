package seedu.duke.task;

import seedu.duke.storage.Storage;

import java.util.ArrayList;

/**
 * Defines a TaskList object that stores instances of Task.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Creates a TaskList with the same contents as the TaskList of the given Storage.
     * @param storage Storage from which this TaskList is to copy its initial content of Tasks.
     */
    public TaskList(Storage storage) {
        this();
        TaskList sourceList = storage.getTaskList();
        for (int i = 0; i < sourceList.size(); i++) {
            this.taskList.add(sourceList.get(i));
        }
    }

    /**
     * Creates a TaskList with an initial capacity of 100;
     * Initialises an ArrayList of Tasks with initial capacity of 100 to implement this TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Adds the given Task to this TaskList.
     * @param task Task to be added to this TaskList.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the given Task from this TaskList.
     * @param task Task to be removed from this TaskList.
     */
    public void remove(Task task) {
        taskList.remove(task);
    }

    /**
     * Returns the size of this TaskList.
     * @return An integer representing the number of Tasks in this TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the Task at the specified position of this TaskList.
     * @param i Position of the Task to be retrieved, from index 0 to n-1 in a TaskList of n elements.
     * @return Task at given index of this TaskList.
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Returns a TaskList with search results for this TaskList.
     * @param taskString Keywords to be used to search this TaskList for relevant Tasks.
     * @return TaskList containing keywords searched for in the given taskString.
     */
    public TaskList find(String taskString) {
        TaskList searchResults = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getTaskString().contains(taskString)
                    || taskString.contains(task.getTaskString())) {
                searchResults.add(task);
            } else if (task.getIsDone() == true && taskString.equals("done")) {
                searchResults.add(task);
            } else if (task.getIsDone() == false && (taskString.equals("undone")
                    || taskString.contains("not done"))) {
                searchResults.add(task);
            } else if (task.getType().toString().toLowerCase().contains(taskString.toLowerCase())
                    || taskString.toLowerCase().contains(task.getType().toString().toLowerCase())) {
                searchResults.add(task);
            } else if (task.getDateAndTime() != null
                    && task.getDateAndTime().toString().contains(taskString)
                    || taskString.contains(task.getTaskString())) {
                searchResults.add(task);
            } else if ((task.getType().toString().equals("EVENT") && taskString.equals("at"))
                    || (task.getType().toString().equals("DEADLINE") && taskString.equals("by"))) {
                searchResults.add(task);
            }
        }
        return searchResults;
    }

    /**
     * Returns the String representation of a TaskList instance.
     * Formats elements in the TaskList as a list, numbered from 1 to n, where n is the size of the taskList.
     * @return String representation of a TaskList instance.
     */
    @Override
    public String toString() {
        String out = "";
        int count = 1;
        for (Task task : taskList) {
            out += count + "." + task + "\n";
            count++;
        }
        return out.trim();
    }
}