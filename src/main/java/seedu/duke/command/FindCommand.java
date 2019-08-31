package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that lists out all Tasks in the TaskList to the user.
 */
public class FindCommand extends Command {
    /** Executes a ListCommand object using information stored in instance attributes.
     * Displays a message that shows the String representation of the given TaskList when called.
     * @param tasks TaskList that was being used by a client of this command.
     * @param ui Ui with methods that are called to display a message showing the String representation of the
     *           given tasklist.
     * @param storage Storage that contains saved String representation of the given TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
