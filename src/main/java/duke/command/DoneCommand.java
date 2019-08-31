package duke.command;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.validation.Validation;

public class DoneCommand extends Command {

    String taskString;

    public DoneCommand(String taskString) {
        this.taskString = taskString.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int i = Validation.getValidatedListIndex(tasks, taskString);
        Task task = tasks.get(i);
        task.setIsDone(true);
        storage.save(tasks);
        ui.showDoneMessage(task.doneMessage(task));
    }
}
