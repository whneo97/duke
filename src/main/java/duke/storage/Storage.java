package duke.storage;

import duke.dateandtime.DateAndTime;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;
import org.w3c.dom.xpath.XPathResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    String filePath;
    File file;
    ArrayList<Task> taskList;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage load() throws DukeException {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) { // creates new file if file does not exist
                this.taskList = new ArrayList<>(100);
            } else {
                this.taskList = Parser.parse(this);
            }
            return this;
        } catch (IOException ex) {
            throw new DukeException("Error loading specified file.");
        }
    }

    public void save(TaskList tasks){
        try{
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
        } catch (Exception e) { System.out.println(e); }
    }


    public File getFile() {
        return file;
    }

    public String getFilePath() {
        return filePath;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
