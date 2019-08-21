import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        System.out.println(logo + "\nHello! I'm Duke\n"
        + "What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {

                String command = sc.next();
                String taskString = "";
                if (sc.hasNextLine()) {
                    taskString = sc.nextLine().trim();
                }

                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("done")) {
                    int n = Task.getValidatedListIndex(taskString);
                    Task.setDone(n - 1);
                } else if (command.equals("delete")) {
                    int n = Task.getValidatedListIndex(taskString);
                    Task.deleteTask(n- 1);
                } else if (command.equals("list")) {
                    Task.ensureEmptyTaskString(taskString);
                    Task.printTaskList();
                } else {
                    if (command.equals("todo")) {
                        Todo todo = new Todo(taskString);
                    } else if (command.equals("deadline")) {
                        Deadline deadline = new Deadline(taskString);
                    } else if (command.equals("event")) {
                        Event event = new Event(taskString);
                    } else {
                        if (command.equals("")) {
                            throw new DukeException("Command cannot be empty.");
                        } else {
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }
                    }
                }
            } catch (DukeException dukeExc) {
                System.out.println("OOPS!!! " + dukeExc.getMessage());
            }
        }

    }
}
