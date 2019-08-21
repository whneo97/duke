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
                String taskString = command + sc.nextLine();
                Task task = new Task(taskString);
            }
        }

    }
}
