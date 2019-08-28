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
     * Deletes a task at index n of the taskList.
     * @param n A number from 0 to size of taskList representing the index of the element in the taskList
     *          at position n.
     */

    /**
     * Sets the element at index n of the taskList to have a state of done.
     * @param n A number from 0 to size of taskList representing the index of the element in the taskList
     *          at position n.
     */
    public void setDone(int n) {
        Task task = taskList.get(n);
        task.isDone = "[+]";
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
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
