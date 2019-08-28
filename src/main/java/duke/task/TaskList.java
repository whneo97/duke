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
