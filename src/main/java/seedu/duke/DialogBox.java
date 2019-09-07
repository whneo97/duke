package seedu.duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.Collections;

/**
 * A custom control using FXML that represents a dialog box.
 * Dialog box consists of an ImageView to represent the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates an instance of DialogBox that stores a String and an Image.
     * @param text String representing a message from a party in a conversation.
     * @param img An avatar representing the party who sent the given input message.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (text.equals(Ui.showLogo())) {
            dialog.setFont(Font.font("Courier", FontWeight.BOLD, 16));
            dialog.setTextFill(Color.YELLOW);
            Glow glow = new Glow();
            glow.setLevel(10);
            dialog.setEffect(glow);
        } else {
            dialog.setFont(Font.font("Calibri", 16));
            dialog.setTextFill(Color.WHITE);
        }
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.setPadding(new Insets(0,0,0,7));
    }

    /**
     * Returns a DialogBox object that represents the User's part of the dialog.
     * @param text String representation of text input from the user.
     * @param img Image representing the user in the chat application.
     * @return DialogBox object representing the User's part of the dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a DialogBox object that represents Duke's part of the dialog.
     * @param text String representation of text input from Duke.
     * @param img Image representing Duke in the chat application.
     * @return DialogBox object representing Duke's part of the dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}