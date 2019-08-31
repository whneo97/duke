package seedu.duke.storage;

import seedu.duke.dateandtime.DateAndTime;
import seedu.duke.exceptions.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Defines a Storage object.
 * Contains a file path and TaskList stored in the file that the file path points to.
 */
public class Storage {

    private String filePath;
    private String dirPath;
    private TaskList taskList;

    /**
     * Creates a Storage object given a file path.
     * Stores the file path as an attribute of this particular instance of the Storage class.
     * @param filePath String representation of file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.dirPath = getDirAsString(filePath);
    }

    /**
     * Returns a String representation of the directory the file at filePath is in.
     * @param filePath Path of file to get directory of.
     * @return String representation of the directory the file at filePath is in.
     */
    public static String getDirAsString(String filePath) {
        int index = 0;
        char dirSeparator = System.getProperty("os.name").startsWith("Windows") ? '\\' : '/';
        for (int i = filePath.length() - 2; i >= 0; i--) {
            if (filePath.charAt(i) == dirSeparator) {
                index = i;
                break;
            }
        }
        return filePath.substring(0, index);
    }

    /**
     * Returns a new Storage object that contains TaskList of Tasks parsed from the file path of this storage.
     * @return Storage object containing TaskList of Tasks parsed from this storage's file path attribute.
     * @throws DukeException If reading an existing file or creating a new file is unsuccessful.
     */
    public Storage load() throws DukeException {
        try {
            File dir = new File(dirPath);
            dir.mkdir();

            File file = new File(filePath);

            if (file.createNewFile()) {
                this.taskList = new TaskList();
            } else {
                this.taskList = Parser.parse(this);
            }
            return this;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new DukeException("Error loading specified file.");
        }
    }

    /**
     * Saves given TaskList to the file located at this Storage's file path.
     * @param tasks TaskList of Tasks, presumably stored in current memory.
     * @throws DukeException If writing to the original file at this Storage's file path is unsuccessful.
     */
    public void save(TaskList tasks) throws DukeException {
        try{
            File dir = new File(dirPath);
            boolean directoryIsCreated = dir.mkdir();

            File file = new File(filePath);
            boolean fileIsCreated = file.createNewFile();
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                char type = task.getType().toString().charAt(0);
                char isDone = task.getIsDone() == true ? '1' : '0';
                String taskString = task.getTaskString();
                DateAndTime dateAndTime = task.getDateAndTime();
                String out = "";

                if (task.getDateAndTime() != null) {
                    out = type + " | " + isDone + " | " + taskString + " | " + dateAndTime + "\n";
                } else {
                    out = type + " | " + isDone + " | " + taskString + "\n";
                }

                fileWriter.append(out);
            }
            fileWriter.close();

            if (directoryIsCreated) {
                throw new DukeException("Original storage directory no longer exists!\n"
                        + "Don't worry, we have restored a new one in it's place in the same location and "
                        + "performed the required operation.");
            }

            if (fileIsCreated) {
                throw new DukeException("Original storage file no longer exists!\n"
                        + "Don't worry, we have restored a new one in it's place in the same location and "
                        + "performed the required operation.");
            }
        } catch (DukeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DukeException("Critical Error: Saving Unsuccessful.");
        }
    }

    /**
     * Returns the file path of this Storage instance as a String.
     * @return This Storage's filePath attribute.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Returns the TaskList stored in this instance of Storage
     * @return This Storage's taskList attribute.
     */
    public TaskList getTaskList() {
        return taskList;
    }
}
