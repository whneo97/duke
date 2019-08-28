public class DeleteCommand extends Command {

    String taskString;

    public DeleteCommand(String taskString) {
        super();
        this.taskString = taskString.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int i = Validation.getValidatedListIndex(tasks, taskString);
        Task task = tasks.get(i);
        tasks.remove(task);
        storage.save(tasks);
        ui.showDeletedMessage(task.deletedMessage(tasks));
    }
}
