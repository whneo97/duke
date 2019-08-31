package seedu.duke.ui;

import seedu.duke.task.TaskList;

import java.util.Scanner;

/**
 * Defines a Ui class.
 * Interacts with user of the program.
 */
public class Ui {

    private Scanner sc;

    /**
     * Creates an instance of Ui.
     * Initialises a Scanner object stored in the sc attribute of this Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
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

    /**
     * Returns the next line of text obtained from user input.
     * @return Next line of text obtained from user input via Scanner stored in sc attribute.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the separator line between program inputs / outputs to the user.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a loading error message to the user.
     * Pre-condition: An error is thrown to the client that calls this method, specifically when loading a file.
     */
    public void showLoadingError() {
        System.out.println("Error loading specified file.");
    }

    /**
     * Displays an error message to the user with the given message.
     * Pre-condition: An error is thrown to the client that calls this method.
     * @param message Error message from Exception that is thrown to a client that calls this method.
     */
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    /**
     * Displays to the user that a Task has been added to a TaskList.
     * Presumably called by the Task class when a client adds it to a TaskList.
     * @param addedMessage Message to indicate to the user that a Task has been added to a TaskList.
     */
    public void showAddedMessage(String addedMessage) {
        System.out.println(addedMessage);
    }

    /**
     * Displays to the user that a Task has been removed from a TaskList.
     * Presumably called by the Task class when a client removes it from a TaskList.
     * @param deletedMessage Message to indicate to the user that a Task has been removed from a TaskList.
     */
    public void showDeletedMessage(String deletedMessage) {
        System.out.println(deletedMessage);
    }

    /**
     * Displays to the user that a Task has been marked as done (completed).
     * Presumably called by the Task class when a client marks it as done.
     * @param doneMessage Message to indicate to the user that a Task has been marked as done.
     */
    public void showDoneMessage(String doneMessage) {
        System.out.println(doneMessage);
    }

    /**
     * Displays to the user the String representation of a TaskList.
     * Presumably called by the toString method of the TaskList class.
     * @param tasks TaskList to be displayed as its String representation.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    /**
     * Displays to the user an exit message before the program receives a command to be terminated.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}