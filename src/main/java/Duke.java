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
                    String s = sc.nextLine().trim();
                    if (s.equals("")) {
                        throw new DukeException("Please enter a valid numerical value after \"done\" (separated by a space) for the task to be marked as done.\n" +
                                "This number cannot be empty.");
                    }

                    int n = 0;

                    try {
                        n = Integer.parseInt(s);
                        if (n < Task.getTaskList().size() || n > Task.getTaskList().size()) {
                            throw new NumberFormatException();
                        }
                        Task.getTaskList().get(n - 1).setDone(true);
                    } catch (NumberFormatException ex) {
                        if (Task.getTaskList().size() == 1) {
                            throw new DukeException("Please enter a valid numerical value after \"done\" (separated by a space) for the task to be marked as done.\n" +
                                    "There is currently 1 task in the list numbered 1.\n" +
                                    "Input \"list\" to view the full list of tasks.");
                        } else {
                            throw new DukeException("Please enter a valid numerical value after \"done\" (separated by a space) for the task to be marked as done.\n" +
                                    "There are currently " + Task.getTaskList().size() + " tasks in the list that are numbered from 1.\n" +
                                    "Input \"list\" to view the full list of tasks.");
                        }
                    }

                } else if (command.equals("list")) {
                    Task.printTaskList();
                } else {
                    String taskString = sc.nextLine().trim();
                    if (command.equals("todo")) {
                        if (taskString.equals("")) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        Todo todo = new Todo(taskString);
                    } else if (command.equals("deadline")) {
                        if (taskString.equals("")) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } else if (!taskString.contains(" /by ")) {
                            throw new DukeException("Please separate deadline description and date/time by \" /by \"");
                        }
                        Deadline deadline = new Deadline(taskString);
                    } else if (command.equals("event")) {
                        if (taskString.equals("")) {
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
