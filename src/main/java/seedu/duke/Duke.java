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
     * Runs the Duke program.
     * Shows welcome message, waits for user input and performs necessary commands until an exit command is received.
     */
//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }

    public void run(String input) {
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        run(input);
        return ui.getOutput();
    }

    public Ui getUi() {
        return ui;
    }

    public boolean getIsExit() {
        return isExit;
    }

    /**
     * Calls the run method of the Duke program.
     * @param args Array of Strings required as arguments for this main method.
     */
//    public static void main(String[] args) {
//        new Duke().run("");
//    }
}
