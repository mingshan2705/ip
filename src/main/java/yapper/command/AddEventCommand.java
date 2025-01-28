package yapper.command;

import yapper.*;
import yapper.task.*;
import java.io.IOException;

/**
 * Represents a command to add an event task to the task list.
 * Parses the user input to create an {@link Event} and adds it to the {@link TaskList}.
 */
public class AddEventCommand extends Command {
    private final String input;

    /**
     * Creates a new AddEventCommand with the specified input.
     *
     * @param input The user input containing the task description, start time, and end time
     *              in the format: <description> /from <yyyy-MM-ddTHH:mm> /to <yyyy-MM-ddTHH:mm>.
     */
    public AddEventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the AddEventCommand by parsing the input, creating an {@link Event} task,
     * adding it to the {@link TaskList}, displaying a confirmation message through {@link Ui},
     * and saving the updated task list to {@link Storage}.
     *
     * @param tasks   The {@link TaskList} to add the new task to.
     * @param ui      The {@link Ui} to display messages to the user.
     * @param storage The {@link Storage} to save the updated task list.
     * @throws IOException              If an error occurs while saving the task list to storage.
     * @throws IllegalArgumentException If the input format is invalid (missing description, start time, or end time).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] parts = input.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new IllegalArgumentException("Invalid format! Use: event <description> /from <yyyy-MM-ddTHH:mm> /to <yyyy-MM-ddTHH:mm>");
        }
        String description = parts[0].trim();
        String from = parts[1].split(" /to ")[0].trim();
        String to = parts[1].split(" /to ")[1].trim();

        Task event = new Event(description, from, to);
        tasks.addTask(event);
        ui.showMessage("Got it. I've added this task:\n  " + event + "\nNow you have " + tasks.size() + " tasks in the list.");
        storage.saveTasks(tasks.getTasks());
    }
}
