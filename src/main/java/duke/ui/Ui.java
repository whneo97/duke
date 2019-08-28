package duke.ui;

import duke.task.TaskList;

import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        System.out.println(logo + "\nHello! I'm Duke\n"
                + "What can I do for you?");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading specified file.");
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    public void showAddedMessage(String addedMessage) { System.out.println(addedMessage); }

    public void showDeletedMessage(String deletedMessage) { System.out.println(deletedMessage); };

    public void showDoneMessage(String doneMessage) { System.out.println(doneMessage); };

    public void showTaskList(TaskList tasks) { System.out.println(tasks); }

}