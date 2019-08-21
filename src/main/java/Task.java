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
        //printing and adding to tasklist will be handled by individual class
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

    static void ensureEmptyTaskString(String taskString) throws DukeException {
        if (!taskString.equals("")) {
            throw new DukeException("There cannot be any additional characters after this command (other than trailing spaces).");
        }
    }

    static int getValidatedListIndex(String s) throws DukeException {
        if (s.equals("")) {
            throw new DukeException("Please enter a valid numerical value after the intended command (separated by a space).\n" +
                    "The number cannot be empty for this command.");
        }

        int n = 0;

        try {
            n = Integer.parseInt(s);
            if (n <= 0 || n > Task.tasklist.size()) {
                throw new NumberFormatException();
            }
            return n;
        } catch (NumberFormatException ex) {
            if (Task.getTaskList().size() == 1) {
                throw new DukeException("Please enter a valid numerical value from 1 to the tasklist's size after the intended command (separated by a space).\n" +
                        "There is currently 1 task in the list.\n" +
                        "Try entering \"list\" to view the full list of tasks.");
            } else {
                throw new DukeException("Please enter a valid numerical value from 1 to the tasklist's size after the intended command (separated by a space).\n" +
                        "There are currently " + Task.getTaskList().size() + " tasks in the list.\n" +
                        "Try entering \"list\" to view the full list of tasks.");
            }
        }
    }

    static void setDone(int n) {
        Task task = Task.tasklist.get(n);
        task.isDone = "[+]";
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
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

    static void deleteTask(int n) {
        Task task = Task.tasklist.get(n);
        Task.tasklist.remove(task);
        System.out.println("Noted. I've removed this task:\n"
                + task);
        if (Task.tasklist.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + Task.tasklist.size() + " tasks in the list.");
        }
    }

    @Override
    public String toString() {
        return type + isDone + " " + this.taskString;
    }
}
