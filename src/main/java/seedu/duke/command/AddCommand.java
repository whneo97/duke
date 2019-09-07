package seedu.duke.command;

import seedu.duke.dateandtime.DateAndTime;
import seedu.duke.exceptions.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.TaskList;
import seedu.duke.task.Todo;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that adds Tasks into the TaskList.
 * Contains a method that executes commands by adding tasks into the TaskLst.
 * Stores information on the task to be created, so the task can be created when the execute method is
 * called by a client of this class.
 */
public class AddCommand extends Command {

    private String command;
    private String[] strArr;

    private String type;
    private String taskString;
    private DateAndTime dateAndTime;

    /** Creates an instance of AddCommand.
     * Stores to attributes of AddCommand relevant information required to create an AddCommand.
     * @param command String representation of a single-word command.
     * @param taskString String representation of task description.
     * @param dateAndTime DateAndTime object storing the date and times relevant to the task.
     *                    If the task does not require any date or time, null may be passed in as an argument.
     */
    public AddCommand(String command, String taskString, DateAndTime dateAndTime) {
        this.command = command;
        this.taskString = taskString;
        this.dateAndTime = dateAndTime;
    }

    /** Executes an AddCommand object using information stored in instance attributes.
     * Modifies a given TaskList by adding the task defined by its attributes to it.
     * Saves the updated TaskList to the given Storage.
     * Displays a message indicating successful addition after addition process has been completed.
     * @param tasks TaskList to which a given task is to be added by this command.
     * @param ui Ui with methods that are called to display a message upon successful addition of the task.
     * @param storage Storage that contains saved String representation of the given TaskList and is to be modified
     *                upon successful addition of the task.
     * @throws DukeException if execution of the command is unsuccessful.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (command.equals("todo")) {
                Todo todo = new Todo(taskString);
                tasks.add(todo);
                ui.showAddedMessage(todo.addedMessage(tasks));
            } else if (command.equals("deadline")) {
                Deadline deadline = new Deadline(taskString, dateAndTime);
                tasks.add(deadline);
                ui.showAddedMessage(deadline.addedMessage(tasks));
            } else if (command.equals("event")) {
                Event event = new Event(taskString, dateAndTime);
                tasks.add(event);
                ui.showAddedMessage(event.addedMessage(tasks));
            }
            storage.save(tasks, ui);
        } catch (DukeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DukeException("Execution of command unsuccessful.");
        }
    }
}
