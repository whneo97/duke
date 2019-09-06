package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Defines the Duke class
 * Contains the main method that is called to run the program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Creates an instance of Duke with a default location of storage files.
     * Files are stored in a text file, inside the current folder where Duke is currently being run.
     */
    public Duke() {
        this(System.getProperty("user.dir") + "/data/duke.txt");
    }

    /**
     * Creates an instance of Duke with it's Storage for loading and saving data.
     * @param filePath Directory where the data file is located or is to be located.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs an iteration of the Duke program.
     * Performs the necessary input command given an input.
     * @param input String representation of input from the user.
     */
    private void run(String input) {
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Returns the response of Duke after calling it's run method.
     * @param input String representation of input from the user.
     * @return The response of Duke obtained from it's Ui after calling the run method of DUke.
     */
    public String getResponse(String input) {
        run(input);
        return ui.getOutput();
    }

    /**
     * Returns the Ui attribute of this instance of Duke.
     * @return Ui instance employed by this Duke object.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Returns whether Duke has been called to exit after the most recent command.
     *
     * @return A boolean tha denotes whether or not Duke has been called to terminate by user command.
     */
    public boolean getIsExit() {
        return isExit;
    }
}
