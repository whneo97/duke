import java.util.ArrayList;

public class Task {

    static ArrayList<Task> tasklist = new ArrayList<>(100);
    String taskString;
    String isDone = "[ ]";
    String type = null;
    String dateAndTime = "";

    /**
     * Constructor for Task that takes in the type of task intended to be created.
     * Precondition: Task type is a valid type that has its own individual class.
     * Type will be assigned to the class' type attribute.
     * Post-condition: Printing and adding the task to tasklist is to be handled by individual classes of each type.
     * (i.e. client that calls this constructor manually adds the task into the tasklist)
     * @param type Type of task (either todo, deadline or event)
     */
    //
    public Task(String type) {
        this.type = type;
    }

    /**
     * Constructor for Task that takes in type of task intended to be created and description of the task.
     * Post-condition: Task created by this constructor would have been added to the tasklist after calling this
     * constructor.
     * @param type General type of task (currently Todo, Deadline or Event)
     * @param taskString Task description that follows after the given command to create the task
     */
    public Task(String type, String taskString) {
        this.taskString = taskString;
        this.type = type;
        Task.tasklist.add(this);
        this.printAdded();
    }

    /**
     * Pre-condition: The Task that uses this method has been added to the Tasklist.
     * Prints the statement to the user that the particular task has been added into the Tasklist
     */
    public void printAdded() {
        System.out.println("Got it. I've added this task:\n  " + this);
        System.out.print("Now you have " + tasklist.size());
        if (tasklist.size() == 1) {
            System.out.print(" task in the list");
        }  else {
            System.out.print(" tasks in the list");
        }
        System.out.println();
    }

    /**
     * Method that ensures a command that is not meant to have additional strings following behind it does not have
     * additional strings following it (eg. list 7)
     * @param taskString Description of a task, following a command
     * @throws DukeException Exception that is thrown if taskString taken in is non-empty
     */
    public static void ensureEmptyTaskString(String taskString) throws DukeException {
        if (!taskString.equals("")) {
            throw new DukeException("There cannot be any additional characters after this command"
                    + " (other than trailing spaces).");
        }
    }

    /**
     * Takes in a string and ensures that it is a valid positive integer that is within the range of the tasklist
     * (numbered from 0 to n - 1 if there are items in the tasklist, where n is the size of the tasklist).
     * Post-condition: A client of this method may use it to access tasks in the tasklist if the string representing
     * the index of the item in the tasklist is valid, or otherwise user will be prompted to re-enter the command and
     * number.
     * @param s String that represents index of item in tasklist.
     * @return An int that is to be used to access the item in the tasklist. This ensures that any n that is returned
     *     will be able to access the tasklist and that the program can continue running without encountering a general
     *     number format exception that will cause the program to terminate in the event that the index is invalid or
     *     out of bounds
     * @throws DukeException Exception that is thrown if the numeric value of the string taken in does not correspond
     *     to a valid integer that can be used to access any task in the tasklist. A valid number should start from 0
     *     and not equal or exceed the size of the current tasklist.
     */
    public static int getValidatedListIndex(String s) throws DukeException {
        if (s.equals("")) {
            throw new DukeException("Please enter a valid numerical value after the intended command"
                    + " (separated by a space).\n" + "The number cannot be empty for this command.");
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
                throw new DukeException("Please enter a valid numerical value from 1 to the tasklist's size"
                        + " after the intended command (separated by a space).\n"
                        + "There is currently 1 task in the list.\n"
                        + "Try entering \"list\" to view the full list of tasks.");
            } else {
                throw new DukeException("Please enter a valid numerical value from 1 to the tasklist's size"
                        + " after the intended command (separated by a space).\n"
                        + "There are currently " + Task.getTaskList().size() + " tasks in the list.\n"
                        + "Try entering \"list\" to view the full list of tasks.");
            }
        }
    }

    /**
     * Sets the element at index n of the tasklist to have a state of done.
     * @param n A number from 0 to size of tasklist representing the index of the element in the tasklist
     *          at position n.
     */
    public static void setDone(int n) {
        Task task = Task.tasklist.get(n);
        task.isDone = "[+]";
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Prints out all elements in the tasklist as a list, numbered from 1 to n, where n is the size of the tasklist.
     */
    public static void printTaskList() {
        int count = 1;
        for (Task task : Task.getTaskList()) {
            System.out.println(count + "." + task);
            count++;
        }
    }

    /**
     * Client may use this method to access the tasklist.
     * @return Tasklist that contains all tasks that are created.
     */
    public static ArrayList<Task> getTaskList() {
        return Task.tasklist;
    }

    public String getTaskString() {
        return taskString;
    }

    public String getIsDone() {
        return isDone;
    }

    public String getType() {
        return type;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Deletes a task at index n of the tasklist.
     * @param n A number from 0 to size of tasklist representing the index of the element in the tasklist
     *          at position n.
     */
    public static void deleteTask(int n) {
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
