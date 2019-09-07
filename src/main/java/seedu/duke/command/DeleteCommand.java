package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.validation.Validation;

/**
 * Defines a Command object that removes Tasks from the TaskList.
 * Contains a method that executes commands by removing tasks from the TaskLst.
 * Stores information on the task to be removed, so the task can be removed when the execute method is
 * called by a client of this class.
 */
public class DeleteCommand extends Command {

    private String taskString;

    /** Creates an instance of DeleteCommand.
     * @param taskString String representation of task description.
     */
    public DeleteCommand(String taskString) {
        this.taskString = taskString.trim();
    }

    /** Executes a DeleteCommand object using information stored in instance attributes.
     * Modifies a given TaskList by delete the task defined by its attributes to it.
     * Saves the updated TaskList to the given Storage.
     * Displays a message indicating successful removal after removal process has been completed.
     * @param tasks TaskList to which a given task is to be removed by this command.
     * @param ui Ui with methods that are called to display a message upon successful removal of the task.
     * @param storage Storage that contains saved String representation of the given TaskList and is to be modified
     *                upon successful removal of the task.
     * @throws DukeException if execution of the command is unsuccessful.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int i = Validation.getValidatedListIndex(tasks, taskString);
            Task task = tasks.get(i);
            tasks.remove(task);
            ui.showDeletedMessage(task.deletedMessage(tasks));
            storage.save(tasks, ui);
        } catch (DukeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DukeException("Execution of command unsuccessful.");
        }
    }
}
