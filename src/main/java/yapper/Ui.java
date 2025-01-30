package yapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles user interaction and message display.
 */
public class Ui {
    private List<String> messageHistory;

    /**
     * Initializes UI with an empty message history.
     */
    public Ui() {
        this.messageHistory = new ArrayList<>();
    }

    /**
     * Stores the latest chatbot message and prints it.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        messageHistory.add(message);
        System.out.println(message);
    }

    /**
     * Retrieves the last chatbot response message.
     *
     * @return The most recent message, or a default message if none exist.
     */
    public String getLastMessage() {
        return messageHistory.isEmpty() ? "No messages yet." : messageHistory.get(messageHistory.size() - 1);
    }
}
