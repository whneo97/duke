import java.util.ArrayList;

class Task {

    static ArrayList<Task> tasklist = new ArrayList<>(100);
    String taskString;
    String isDone = "[ ]";
    String type = null;
    String dateAndTime = "";

    //Precondition: Task type is a valid type that has its own individual class
    Task(String type) {
        this.type = type;
        Task.tasklist.add(this);
        //printing will be handled by individual class
    }

    Task(String type, String taskString) {
        this.taskString = taskString;
        this.type = type;
        Task.tasklist.add(this);
        this.printAdded();
    }

    void printAdded() {
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
