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

        ArrayList<ArrayList<String>> list = new ArrayList<>(100);

        while (true) {
            String command = sc.nextLine();
            String[] wordArr = command.split(" ");
            String keyword = wordArr[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (keyword.equals("done")) {
                int n = Integer.parseInt(wordArr[1]);
                list.get(n - 1).set(0, "✓");
                System.out.println("Nice! I've marked this task as done:");
                System.out.print("  [✓] ");
                System.out.println(list.get(n - 1).get(1));
            } else if (command.equals("list")) {
                int count = 1;
                for (ArrayList<String> item : list) {
                    System.out.println(count + "." + "[" + item.get(0) + "] " + item.get(1));
                    count++;
                }
            } else {
                list.add(new ArrayList(List.of("✗", command)));
                System.out.println("added: " + command);
            }
        }

    }
}
