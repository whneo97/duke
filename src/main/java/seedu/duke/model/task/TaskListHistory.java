package seedu.duke.model.task;

import seedu.duke.commons.exceptions.historyexceptions.CannotRedoException;
import seedu.duke.commons.exceptions.historyexceptions.CannotUndoException;

import java.util.ArrayList;

public class TaskListHistory {
    private int curr = -1;
    private ArrayList<TaskList> listOfTaskLists;

    public TaskListHistory() {
        this.listOfTaskLists = new ArrayList<>();
    }

    public void add(TaskList taskList) {
        TaskList copy = new TaskList(taskList);
        if (curr == listOfTaskLists.size() - 1) {
            listOfTaskLists.add(copy);
            curr++;
        } else {
            curr++;
            listOfTaskLists.set(curr, copy);
        }
        System.out.println(curr);
    }

    public TaskList undo() throws CannotUndoException {
        if (curr <= 0) {
            System.out.println(curr);
            throw new CannotUndoException("Cannot undo. There are no older versions of the TaskList stored in "
                    + "memory.");
        } else {
            curr--;
            System.out.println(curr);
            return new TaskList(listOfTaskLists.get(curr));
        }
    }

    public TaskList redo() throws CannotRedoException {
        if (curr == listOfTaskLists.size() - 1) {
            throw new CannotRedoException("Cannot redo. There are no newer versions of the TaskList stored in "
                    + "memory");
        } else {
            curr++;
            return new TaskList(listOfTaskLists.get(curr));
        }
    }

    @Override
    public String toString() {
        String s = "TASKLIST HISTORY START-------------------------";
        int n = 1;
        for (TaskList taskList : listOfTaskLists) {
            s += "\nTASKLIST NUMBER " + n + "\n";
            if (n == curr + 1) {
                s += "CURRENT IS HERE\n";
            }
            s += taskList;
            n++;
        }
        s += "\nTASKLIST HISTORY END----------------------------";
        return s;
    }

}
