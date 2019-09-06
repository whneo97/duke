package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that lists out command definitions specified.
 * Instructs users on how to use commands of this program.
 */
public class HelpCommand extends Command {


    // A list of constant definitions and usage instructions for all commands known to the program that may be of
    // help to a user who requests for these definitions.
    private static final String TODOHELP = "todo: Adds a todo into the tasklist.\n"
            + "Requires input to be in the format \'todo [task]\'.";
    private static final String DEADLINEHELP = "deadline: Adds a deadline into the tasklist.\n"
            + "Requires input to be in the format \'deadline [task] /by [DD/MM/YYYY]\', "
            + "or \'deadline [task] /by [DD/MM/YYYY] [HHMM]\', where dates and times (24-hour clock) are valid.";
    private static final String EVENTHELP = "event: Adds an event into the tasklist.\n"
            + "Requires input to be in the format \'deadline [task] /at [DD/MM/YYYY] [HHMM]\', "
            + "where dates and times (24-hour clock) are valid.";
    private static final String DONEHELP = "done: Marks a task in the tasklist as done.\n"
            + "Requires input to be in the format \'done [number representing task in tasklist]\'.";
    private static final String DELETEHELP = "delete: Deletes a task from the tasklist.\n"
            + "Requires input to be in the format \'delete [number representing task in tasklist]\'.";
    private static final String LISTHELP = "list: Lists all task in the tasklist stored.\n"
            + "Requires input to be in the format \'list\'.";
    private static final String FINDHELP = "find: Finds tasks in the tasklist given keywords.\n"
            + "Requires input to be in the format \'find\' [keyword].\n"
            + "Keywords could be name of tasks, substrings of it, type of tasks, date in DD/MM/YYYY format, "
            + "time in HHMM format, or whether or not the task is done in the form \'done\', \'not done\', "
            +  "or \'undone\'.\n"
            + "Keywords are not case-sensitive.";
    private static final String HELPHELP = "help: Shows a list of specified commands or all known commands so far.\n"
            + "Requires input to be in the format \'help\' or \'help [command1] [command2] etc.\'.";
    private static final String UNKNOWN = "Unknown command.";
    private static final String FURTHERPROMPT = "Can't find what you're looking for?\nInput \'help\' "
            + "for the full list of commands known to the this program and information on their usage.";

    private String taskString;

    /**
     * Creates an instance of HelpCommand.
     * Stores to attributes of HelpCommand relevant search information required to create a HelpCommand.
     * @param taskString String representation of keywords user needs help with.
     */
    public HelpCommand(String taskString) {
        this.taskString = taskString;
    }

    /**
     * Returns a String containing compiled definitions for commands requested by a client that calls this method.
     * Takes in variable number of arguments depending on the number of commands required by the user.
     * @param requestedCommandList A list of Strings containing possible commands the user needs help with.
     * @return String containing compiled definitions for commands the user needs help with.
     */
    private String getHelpDefinitions(String...requestedCommandList) {
        String out = "";
        for (String requestedCommand : requestedCommandList) {
            if (requestedCommand.equals("todo")) {
                out += TODOHELP;
            } else if (requestedCommand.equals("deadline")) {
                out += DEADLINEHELP;
            } else if (requestedCommand.equals("event")) {
                out += EVENTHELP;
            } else if (requestedCommand.equals("done")) {
                out += DONEHELP;
            } else if (requestedCommand.equals("delete")) {
                out += DELETEHELP;
            } else if (requestedCommand.equals("list")) {
                out += LISTHELP;
            } else if (requestedCommand.equals("find")) {
                out += FINDHELP;
            } else if (requestedCommand.equals("help")) {
                out += HELPHELP;
            } else {
                out += requestedCommand + ": " + UNKNOWN;
            }
            out += "\n\n";
        }
        return out.trim();
    }
    
    /** Executes a HelpCommand object using information stored in instance attributes.
     * Displays a message that shows all help information requested
     * @param tasks TaskList that was being used by a client of this command.
     * @param ui Ui with methods that are called to display a message showing all command definitions requested.
     * @param storage Storage that contains saved String representation of the given TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String helpDefinitions = "";
        if (taskString.equals("")) {
            helpDefinitions = getHelpDefinitions("todo", "deadline", "event", "done", "delete",
                    "list", "find", "help");
        } else {
            helpDefinitions = getHelpDefinitions(taskString.split(" ")) + "\n\n" + FURTHERPROMPT;
        }
        ui.showHelpMessage(helpDefinitions);
    }
}