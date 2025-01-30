package yapper.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;

/**
 * Represents a dialog box in the chat UI.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a dialog box with a label and image.
     *
     * @param message The message text.
     * @param img The image avatar.
     */
    public DialogBox(String message, Image img) {
        text = new Label(message);
        displayPicture = new ImageView(img);

        text.setWrapText(true);
        text.setMaxWidth(250);
        text.setMinHeight(Region.USE_PREF_SIZE);

        displayPicture.setFitWidth(100);
        displayPicture.setFitHeight(100);

        this.getChildren().addAll(text, displayPicture);
    }

    public DialogBox(Image img, String message) {
        text = new Label(message);
        displayPicture = new ImageView(img);

        text.setWrapText(true);
        text.setMaxWidth(250);
        text.setMinHeight(Region.USE_PREF_SIZE);

        displayPicture.setFitWidth(100);
        displayPicture.setFitHeight(100);

        this.getChildren().addAll(displayPicture, text);
    }

    /**
     * Creates a user dialog box (aligned to the right).
     *
     * @param message The user's message.
     * @param img The user's avatar.
     * @return A DialogBox aligned to the right.
     */
    public static DialogBox getUserDialog(String message, Image img) {
        DialogBox db = new DialogBox("User: " + message, img);
        db.setAlignment(Pos.TOP_RIGHT);
        db.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
        return db;
    }

    /**
     * Creates a Yapper dialog box (aligned to the left).
     *
     * @param message Yapper's message.
     * @param img Yapper's avatar.
     * @return A DialogBox aligned to the left.
     */
    public static DialogBox getYapperDialog(String message, Image img) {
        DialogBox db = new DialogBox(img, "Yapper: " + message);
        db.setAlignment(Pos.TOP_LEFT);
        db.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        return db;
    }
}
