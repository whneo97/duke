package seedu.duke.model.task;

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
     * Search keywords may contain the task description of tasks to be searched for,
     * the status of whether tasks have been marked as done, the types of task, date / time
     * (in DD/MM/YYYY and HHMM format respectively), and separator keywords for the type of task
     * (eg. "at" for event and "by" for deadline).
     * If none of the task descriptions, types, done statuses, dates/ times etc. of all the tasks match the
     * search keyword, the method returns an empty TaskList.
     * @param taskString Keywords to be used to search this TaskList for relevant Tasks.
     * @return TaskList containing keywords searched for in the given taskString.
     */
    public TaskList find(String taskString) {
        TaskList searchResults = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);

            // If search keywords contain the task description or vice versa.
            // the task is added to the list of search results.
            if (task.getTaskString().contains(taskString)
                    || taskString.contains(task.getTaskString())) {
                searchResults.add(task);
            // If search keyword is "done" (i.e. search for tasks which have been marked as completed,
            // the task is added to the list of search results if it has been marked as done.
            } else if (task.getIsDone() == true && taskString.equals("done")) {
                searchResults.add(task);
            // If search keyword is "undone" or "not done"
            // (i.e. search for tasks which have yet to be marked as completed),
            // the task is added to the list of search results if it has yet to be marked as done.
            } else if (task.getIsDone() == false && (taskString.equals("undone")
                    || taskString.contains("not done"))) {
                searchResults.add(task);
            // If search keywords contain type of task (eg. deadline, event etc.),
            // the task is added to the list of search results if it's type matches the type to be searched for.
            // Type of task can be in any case (uppercase / lowercase).
            } else if (task.getType().toString().toLowerCase().contains(taskString.toLowerCase())
                    || taskString.toLowerCase().contains(task.getType().toString().toLowerCase())) {
                searchResults.add(task);
            // If search keywords are contained in the date (DD/MM/YYYY format) or time (HHMM format) attributes
            // of the task, the task is added to the list of search results.
            } else if (task.getDateAndTime() != null
                    && task.getDateAndTime().toString().contains(taskString)
                    || taskString.contains(task.getTaskString())) {
                searchResults.add(task);
            // If search keyword is "at" for an event-type task or search keyword is "by" for an event-type task,
            // the task is added to the list of search results if it is an event-type or
            // deadline type task respectively.
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
