package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.dateandtime.DateAndTime;
import seedu.duke.exceptions.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.TaskList;
import seedu.duke.task.Todo;
import seedu.duke.validation.DeadlineValidation;
import seedu.duke.validation.EventValidation;
import seedu.duke.validation.LoadValidation;
import seedu.duke.validation.Validation;

import java.io.File;
import java.util.Scanner;

/**
 * Defines a Parse object.
 * Contains methods to parse contents in storage files and user input.
 */
public class Parser {

    /**
     * Returns a Command object given a full line of text.
     * @param fullCommand Line of instructions presumably from user input.
     * @return Command object based on information extracted from full command given.
     * @throws DukeException If full command taken in is invalid and cannot be used to return any Command.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Scanner sc = new Scanner(fullCommand.trim());

        if (!sc.hasNext()) {
            Validation.ensureNonEmptyCommand("");
        }
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
        } else if (command.equals("find")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            return new FindCommand(taskString);
        } else if (command.equals("done")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            return new DoneCommand(taskString);
        } else if (command.equals("delete")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            return new DeleteCommand(taskString);
        } else if (command.equals("help")) {
            return new HelpCommand(taskString);
        } else {
            Validation.ensureNonEmptyCommand(command);
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns a TaskList of Tasks given a Storage containing the storage-format String representation of a TaskList.
     * Creates Tasks based on tokens from the Storage and adds them to the TaskList to be returned
     * @param storage Storage containing the storage-format String representation of a TaskList to be parsed.
     * @return TaskList of Tasks extracted from the given Storage.
     * @throws DukeException If there are invalid tokens in the Storage that cannot be converted to Tasks or
     *                       adding the Tasks into the TaskList is unsuccessful.
     */
    public static TaskList parse(Storage storage) throws DukeException {
        try {
            File file = new File(storage.getFilePath());
            Scanner sc = new Scanner(file);
            TaskList taskList = new TaskList();
            while (sc.hasNextLine()) {
                String[] arr = sc.nextLine().split(" \\| ");
                String type = LoadValidation.getValidatedTaskType(arr[0].trim());
                LoadValidation.ensureValidNumberOfTokens(type, arr);
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
