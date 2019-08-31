package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command{

    String taskString;

    public FindCommand(String taskString) {
        this.taskString = taskString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showSearchMessage();
        ui.showTaskList(tasks.find(taskString));
    }
}
