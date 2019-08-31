package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.validation.Validation;

/**
 * Defines a Command object that marks Tasks in the TaskList as done.
 * Contains a method that executes commands by marking tasks in the TaskLst as done.
 * Stores information on the task to be marked as done, so the task can be marked as done when the execute method is
 * called by a client of this class.
 */
public class DoneCommand extends Command {

    private String taskString;

    /** Creates an instance of DoneCommand.
     * @param taskString String representation of task description.
     */
    public DoneCommand(String taskString) {
        this.taskString = taskString.trim();
    }

    /** Executes a DoneCommand object using information stored in instance attributes.
     * Modifies a given TaskList by marking the task defined by its attributes as done.
     * Saves the updated TaskList to the given Storage.
     * Displays a message indicating successful marking after marking process has been completed.
     * @param tasks TaskList to which a given task is to be marked as sdone by this command.
     * @param ui Ui with methods that are called to display a message upon successful marking of the task.
     * @param storage Storage that contains saved String representation of the given TaskList and is to be modified
     *                upon successful marking of the task.
     * @throws DukeException if execution of the command is unsuccessful.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int i = Validation.getValidatedListIndex(tasks, taskString);
            Task task = tasks.get(i);
            task.setIsDone(true);
            storage.save(tasks);
            ui.showDoneMessage(task.doneMessage());
        } catch (DukeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DukeException("Execution of command unsuccessful.");
        }
    }
}
