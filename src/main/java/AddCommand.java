public class AddCommand extends Command{

    String command;
    String[] strArr;

    String type;
    String taskString;
    DateAndTime dateAndTime;

    public AddCommand(String command, String taskString, DateAndTime dateAndTime) {
        this.command = command;
        this.taskString = taskString;
        this.dateAndTime = dateAndTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (command.equals("todo")) {
            Todo todo = new Todo(taskString);
            tasks.add(todo);
            storage.save(tasks);
            ui.showAddedMessage(todo.addedMessage(tasks));
        } else if (command.equals("deadline")) {
            Deadline deadline = new Deadline(taskString, dateAndTime);
            tasks.add(deadline);
            storage.save(tasks);
            ui.showAddedMessage(deadline.addedMessage(tasks));
        } else if (command.equals("event")) {
            Event event = new Event(taskString, dateAndTime);
            tasks.add(event);
            storage.save(tasks);
            ui.showAddedMessage(event.addedMessage(tasks));

        }
    }
}
