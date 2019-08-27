import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void save(){
        try{
            FileWriter fileWriter = new FileWriter("/Users/whneo97/OneDrive/UNI/CS/Y2S1/CS2103/duke/data/duke.txt");
            for (Task task : Task.tasklist) {
                char type = task.getType().charAt(1);
                char isDone = task.getIsDone().charAt(1) == '+' ? '1' : '0';
                String taskString = task.getTaskString();
                String dateAndTime = task.getDateAndTime();
                String out = "";

                if (task.day != -1) {
                    out = type + " | " + isDone + " | " + taskString + " | " + dateAndTime + "\n";
                } else {
                    out = type + " | " + isDone + " | " + taskString + "\n";
                }

                fileWriter.append(out);
            }
            fileWriter.close();
        } catch (Exception e) { System.out.println(e); }
    }

    public static void load() throws IOException, DukeException {
        File file = new File("/Users/whneo97/OneDrive/UNI/CS/Y2S1/CS2103/duke/data/duke.txt");
        if (!file.createNewFile()) {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] arr = sc.nextLine().split(" \\| ");
                String type = arr[0].trim();
                String isDone = arr[1].trim().equals("1") ? "[+]" : "[ ]";
                String taskString = arr[2].trim();
                int day = 0;
                int month = 0;
                int year = 0;
                int hour = -1;
                int minute = -1;
                int hourEnd = -1;
                int minuteEnd = -1;
                if (type.equals("E") || type.equals("D")) {

                    String[] dateTimeArr = arr[3].trim().split(" ");
                    String[] dateArr = dateTimeArr[0].split("/");
                    day = Integer.parseInt(dateArr[0]);
                    month = Integer.parseInt(dateArr[1]);
                    year = Integer.parseInt(dateArr[2]);

                    if (type.equals("E")) {
                        String[] timeArr = dateTimeArr[1].split("-");
                        String timeStart = timeArr[0];
                        String timeEnd = timeArr[1];
                        hour = Integer.parseInt(timeStart.substring(0, 2));
                        minute = Integer.parseInt(timeStart.substring(2, 4));
                        hourEnd = Integer.parseInt(timeEnd.substring(0, 2));
                        minuteEnd = Integer.parseInt(timeEnd.substring(2, 4));
                    } else if (dateTimeArr.length == 2) {
                        hour = Integer.parseInt(dateTimeArr[1].substring(0, 2));
                        minute = Integer.parseInt(dateTimeArr[1].substring(2, 4));
                    }
                }
                Task task = new Task("[" + type + "]");
                if (type.equals("T")) {
                    task = new Todo();
                } else if (type.equals("E")) {
                    task = new Event();
                } else {
                    task = new Deadline();
                }

                task.setIsDone(isDone);
                task.setTaskString(taskString);
                task.setDay(day);
                task.setMonth(month);
                task.setYear(year);
                task.setHour(hour);
                task.setMinute(minute);
                task.setHourEnd(hourEnd);
                task.setMinuteEnd(minuteEnd);

                Task.getTaskList().add(task);
            }
        }
    }

    /** Main method for interactive Duke program that interacts with user depending on user input.
     * User may add tasks - todo, deadline, event -, list all tasks, mark tasks as done, delete tasks and
     * exit the program. The program validates each line of command the user keys in and takes the first word
     * to be the command corresponding to each function in the interface, ensuring that commands are not empty, and
     * that commands are within the scope known to the program. If user input is invalid, the program throws a
     * DukeException and awaits the next user input. Program terminates when user enters "bye"
     * @param args String[] args of main method
     */
    public static void main(String[] args) throws IOException, DukeException {
        load();
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
                    Task.ensureEmptyTaskString(taskString);
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("done")) {
                    int n = Task.getValidatedListIndex(taskString);
                    Task.setDone(n - 1);
                    save();
                } else if (command.equals("delete")) {
                    int n = Task.getValidatedListIndex(taskString);
                    Task.deleteTask(n - 1);
                    save();
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
                    save();
                }
            } catch (DukeException dukeExc) {
                System.out.println("OOPS!!! " + dukeExc.getMessage());
            }
        }

    }
}
