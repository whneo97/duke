package duke.task;

import duke.storage.Storage;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskList;

    public TaskList(Storage storage) {
        this.taskList = storage.getTaskList();
    }

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void remove(Task task) { taskList.remove(task); }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

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
     * Prints out all elements in the taskList as a list, numbered from 1 to n, where n is the size of the taskList.
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
