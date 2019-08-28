import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        Scanner sc = new Scanner(fullCommand.trim());

        String command = sc.next();
        String taskString = "";
        if (sc.hasNextLine()) {
            taskString = sc.nextLine().trim();
        }

        if (command.equals("bye")) {
            Validation.ensureEmptyTaskString(command, taskString);
            return new ExitCommand();
        } else if (command.equals("list")) {
            Validation.ensureEmptyTaskString(command, taskString);
            return new ListCommand();
        } else if (command.equals("todo")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            return new AddCommand("todo", taskString, null);
        } else if (command.equals("deadline")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            String[] descriptionAndDateTime = DeadlineValidation.getValidatedDescriptionAndDateTime(taskString);
            taskString = descriptionAndDateTime[0];
            String dateTimeString = descriptionAndDateTime[1];

            DateAndTime dateAndTime = DeadlineValidation.getValidatedDateAndTime(dateTimeString);

            return new AddCommand("deadline", taskString, dateAndTime);
        } else if (command.equals("event")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            String[] descriptionAndDateTime = EventValidation.getValidatedDescriptionAndDateTime(taskString);
            taskString = descriptionAndDateTime[0];
            String dateTimeString = descriptionAndDateTime[1];

            DateAndTime dateAndTime = EventValidation.getValidatedDateAndTime(dateTimeString);

            return new AddCommand("event", taskString, dateAndTime);
        } else if (command.equals("done")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            return new DoneCommand(taskString);
        } else if (command.equals("delete")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            return new DeleteCommand(taskString);
        } else {
             Validation.ensureNonEmptyCommand(command);
             throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static ArrayList<Task> parse(Storage storage) throws DukeException {
        try {
            File file = new File(storage.filePath);
            Scanner sc = new Scanner(file);
            ArrayList<Task> taskList = new ArrayList<>(100);
            while (sc.hasNextLine()) {
                String[] arr = sc.nextLine().split(" \\| ");
                String type = LoadValidation.getValidatedTaskType(arr[0].trim());
                LoadValidation.ensureValidTokens(type, arr);
                boolean isDone = LoadValidation.getValidatedDoneStatus(arr[1].trim()) == 1 ? true : false;
                String taskString = arr[2].trim();
                DateAndTime dateAndTime = null;
                Validation.ensureNonEmptyTaskString(type, taskString);
                if (type.equals("E")) {
                    dateAndTime = EventValidation.getValidatedDateAndTime(arr[3]);
                    taskList.add(new Event(taskString, dateAndTime, isDone));
                } else if (type.equals("D")) {
                    dateAndTime = DeadlineValidation.getValidatedDateAndTime(arr[3]);
                    taskList.add(new Deadline(taskString, dateAndTime, isDone));
                } else {
                    taskList.add(new Todo(taskString, isDone));
                }
            }
            return taskList;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DukeException("We cannot find your file.");
        }
    }
}
