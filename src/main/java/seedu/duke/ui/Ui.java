package seedu.duke.ui;

import seedu.duke.task.TaskList;

import java.util.Scanner;

/**
 * Defines a Ui class.
 * Interacts with user of the program.
 */
public class Ui {

    private Scanner sc;
    private String input;
    private String output;

    /**
     * Creates an instance of Ui.
     * Initialises a Scanner object stored in the sc attribute of this Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns a String that displays the welcome message to the user.
     * @return String representation of welcome message to be displayed to the user.
     */
    public static String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        return logo + "\nHello! I'm Duke\n"
                + "What can I do for you?";
    }

    /**
     * Returns the next line of text obtained from user input.
     * @return Next line of text obtained from user input via Scanner stored in sc attribute.
     */
    public String readCommand() {
        input = sc.nextLine();
        return input;
    }

    /**
     * Stores the separator line between program inputs / outputs to be displayed to the user.
     */
    public void showLine() {
        output = "____________________________________________________________";
    }

    /**
     * Stores a loading error message to be displayed to the user.
     * Pre-condition: An error is thrown to the client that calls this method, specifically when loading a file.
     */
    public void showLoadingError() {
        output = "Error loading specified file.";
    }

    /**
     * Stores an error message to be displayed to the user with the given message.
     * Pre-condition: An error is thrown to the client that calls this method.
     * @param message Error message from Exception that is thrown to a client that calls this method.
     */
    public void showError(String message) {
        output = "OOPS!!! " + message;
    }

    /**
     * Stores a message to be displayed to the user that a Task has been added to a TaskList.
     * Presumably called by the Task class when a client adds it to a TaskList.
     * @param addedMessage Message to indicate to the user that a Task has been added to a TaskList.
     */
    public void showAddedMessage(String addedMessage) {
        output = addedMessage;
    }

    /**
     * Stores a message to be displayed to the user that a Task has been removed from a TaskList.
     * Presumably called by the Task class when a client removes it from a TaskList.
     * @param deletedMessage Message to indicate to the user that a Task has been removed from a TaskList.
     */
    public void showDeletedMessage(String deletedMessage) {
        output = deletedMessage;
    }

    /**
     * Stores a message to be displayed to the user that a Task has been marked as done (completed).
     * Presumably called by the Task class when a client marks it as done.
     * @param doneMessage Message to indicate to the user that a Task has been marked as done.
     */
    public void showDoneMessage(String doneMessage) {
        output = doneMessage;
    }

    /**
     * Stores a message to the be displayed to the user the message that a search on a TaskList has been completed.
     * Completed message is followed by search results.
     * @param searchResults TaskList containing list of search results based on keyword from user input.
     */
    public void showSearchResults(TaskList searchResults) {
        output = "Here are the matching tasks in your list:\n"
                + searchResults;
    }

    /**
     * Stores a message to be displayed to the user the String representation of a TaskList.
     * Presumably called by the toString method of the TaskList class.
     * @param tasks TaskList to be displayed as its String representation.
     */
    public void showTaskList(TaskList tasks) {
        output = tasks.toString();
    }

    /**
     * Stores a messsage to be displayed to the user an exit message.
     * This message is displayed before the program terminates.
     */
    public void showExitMessage() {
        output = "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the current input of this instance of Ui.
     * @return Current input taken in and stored in this instance of Ui.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Returns the current output of this instance of Ui.
     * @return Current output stored in this instance of Ui.
     */
    public String getOutput() {
        return this.output;
    }

}