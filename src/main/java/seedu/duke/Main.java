package seedu.duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.duke.model.Duke;
import seedu.duke.ui.MainWindow;

import java.io.IOException;

/**
 * A GUI for seedu.duke.model.Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Start method called to start the Duke program by building the necessary JavaFx components.
     * @param stage A Stage taken in where the GUI of Duke program is run.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "fxmlLoader for Main class is null";
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().addAll(this.getClass().getResource("/view/style.css").toExternalForm());
            stage.setTitle("Duke V2.2");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}