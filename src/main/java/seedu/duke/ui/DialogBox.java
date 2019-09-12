package seedu.duke.ui;

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

import java.io.IOException;
import java.io.InputStream;
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

    private void setDialogFont(String resourcePath, int fontSize) {
        InputStream fontStream = MainWindow.class.getResourceAsStream(resourcePath);
        if (fontStream != null) {
            try {
                Font bgFont = Font.loadFont(fontStream, fontSize);
                fontStream.close();
                dialog.setFont(bgFont);
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
        dialog.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
    }

    /**
     * Creates an instance of DialogBox that stores a String and an Image.
     * @param text String representing a message from a party in a conversation.
     * @param img An avatar representing the party who sent the given input message.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            assert fxmlLoader != null : "fxmlLoader for DialogBox is null";
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (text.equals(Ui.showLogo())) {
            setDialogFont("/view/Courier.ttf", 16);
//            dialog.setFont(Font.font("Courier", FontWeight.BOLD, 16));
            dialog.setTextFill(Color.YELLOW);
            Glow glow = new Glow();
            glow.setLevel(15);
            dialog.setEffect(glow);
        } else {
            setDialogFont("/view/Calibri.ttf", 16);
//            dialog.setFont(Font.font("Calibri", 16));
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
        assert img != null : "Duke profile image is null.";
        var db = new DialogBox(text, img);
        //Colour Experimentation
        //db.dialog.setTextFill(Color.rgb(150, 255, 160));
        return db;
    }

    /**
     * Returns a DialogBox object that represents Duke's part of the dialog.
     * @param text String representation of text input from Duke.
     * @param img Image representing Duke in the chat application.
     * @return DialogBox object representing Duke's part of the dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        assert img != null : "User profile image is null.";
        var db = new DialogBox(text, img);
        //Colour Experimentation:
        //if (!text.equals(Ui.showLogo())) {
        //db.dialog.setTextFill(Color.rgb(255, 255, 180));
        //}
        db.flip();
        return db;
    }
}