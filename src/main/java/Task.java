import java.util.ArrayList;

class Task {

    static ArrayList<Task> tasklist = new ArrayList<>(100);
    String taskString;
    String isDone = "[ ]";
    String type = null;

    Task(String type, String taskString) {
        this.type = type.equals("deadline") ? "[D]" : type.equals("event") ? "[E]" : "[T]";
        if (this.type.equals("[E]")) {
            String[] arr = taskString.split(" /at ");
            this.taskString = arr[0] + " (at: " + arr[1] + ")";
        } else if (this.type.equals("[D]")) {
            String[] arr = taskString.split(" /by ");
            this.taskString = arr[0] + " (by: " + arr[1] + ")";
        } else {
            this.taskString = taskString;
        }
        Task.tasklist.add(this);
        System.out.println("Got it. I've added this task:\n  " + this);
        System.out.print("Now you have " + tasklist.size());
        if (tasklist.size() == 1) {
            System.out.print(" task in the list");
        }  else {
            System.out.print(" tasks in the list");
        }
        System.out.println();
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
        return type + isDone + " " + this.taskString;
    }
}
