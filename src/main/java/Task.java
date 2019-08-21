import java.util.ArrayList;

class Task {

    static ArrayList<Task> tasklist = new ArrayList<>(100);
    String taskString;
    String isDone = "[ ]";

    Task(String taskString) {
        this.taskString = taskString;
        Task.tasklist.add(this);
        System.out.println("added: " + this.taskString);
    }

    void setDone(boolean isDone) {
        this.isDone = "[+]";
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this);
    }

    static void printTaskList() {
        int count = 1;
        for (Task task : Task.getTaskList()) {
            System.out.println(count + "." + task);
            count++;
        }
    }

    static ArrayList<Task> getTaskList() {
        return Task.tasklist;
    }

    @Override
    public String toString() {
        return isDone + " " + this.taskString;
    }
}
