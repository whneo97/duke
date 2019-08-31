package duke.command;

import duke.dateandtime.DateAndTime;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class FindCommand extends Command{

    String command;
    String[] strArr;

    String type;
    String taskString;
    DateAndTime dateAndTime;

    public FindCommand(String command, String taskString, DateAndTime dateAndTime) {
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
