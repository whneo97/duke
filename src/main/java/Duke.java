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

                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("done")) {
                    int n = Integer.parseInt(sc.next());
                    Task.getTaskList().get(n - 1).setDone(true);
                } else if (command.equals("list")) {
                    Task.printTaskList();
                } else {
                    String taskString = sc.nextLine().trim();
                    if (command.equals("todo")) {
                        if (taskString.trim().equals("")) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        Todo todo = new Todo(taskString);
                    } else if (command.equals("deadline")) {
                        if (taskString.trim().equals("")) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } else if (!taskString.contains(" /by ")) {
                            throw new DukeException("Please separate deadline description and date/time by \" /by \"");
                        }
                        Deadline deadline = new Deadline(taskString);
                    } else if (command.equals("event")) {
                        if (taskString.trim().equals("")) {
                            throw new DukeException("The description of an event cannot be empty.");
                        } else if (!taskString.contains(" /at ")) {
                            throw new DukeException("Please separate event description and date/time by \" /at \"");
                        }
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
