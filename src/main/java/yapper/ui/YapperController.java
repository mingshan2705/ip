package yapper.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import yapper.Yapper;

/**
 * Controller for Yapper's chat UI.
 */
public class YapperController {
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField userInput;

    private Yapper yapper;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image yapperImage = new Image(this.getClass().getResourceAsStream("/images/yapper.png"));

    /**
     * Sets the Yapper chatbot instance and ensures scrolling is bound.
     *
     * @param yapper The Yapper instance.
     */
    public void setYapper(Yapper yapper) {
        this.yapper = yapper;

        // ✅ Ensure scrolling updates whenever new messages are added
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        displayWelcomeMessage();
    }

    private void displayWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Yapper \nWhat can I do for you today?";
        DialogBox botDialog = DialogBox.getYapperDialog(welcomeMessage, yapperImage);
        dialogContainer.getChildren().add(botDialog);
    }

    /**
     * Handles user input and displays responses in the chat.
     */
    @FXML
    private void handleUserInput() {
        if (yapper == null) {
            System.err.println("Error: Yapper instance not set in YapperController.");
            return;
        }

        String input = userInput.getText().trim();
        if (input.isEmpty()) return;

        // ✅ User message (flush right)
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        dialogContainer.getChildren().add(userDialog);

        // ✅ Get Yapper's response and flush it left
        String response = yapper.getResponse(input);
        DialogBox botDialog = DialogBox.getYapperDialog(response, yapperImage);
        dialogContainer.getChildren().add(botDialog);

        userInput.clear();
    }
}
