package yapper.command;

import yapper.*;
import yapper.task.*;
import java.io.IOException;

/**
 * Represents a command to add a deadline task to the task list.
 * Parses the user input to create a {@link Deadline} and adds it to the {@link TaskList}.
 */
public class AddDeadlineCommand extends Command {
    private final String input;

    /**
     * Creates a new AddDeadlineCommand with the specified input.
     *
     * @param input The user input containing the task description and deadline
     *              in the format: <description> /by <yyyy-MM-ddTHH:mm>.
     */
    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the AddDeadlineCommand by parsing the input, creating a {@link Deadline} task,
     * adding it to the {@link TaskList}, displaying a confirmation message through {@link Ui},
     * and saving the updated task list to {@link Storage}.
     *
     * @param tasks   The {@link TaskList} to add the new task to.
     * @param ui      The {@link Ui} to display messages to the user.
     * @param storage The {@link Storage} to save the updated task list.
     * @throws IOException              If an error occurs while saving the task list to storage.
     * @throws IllegalArgumentException If the input format is invalid (missing description or deadline).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] parts = input.split(" /by ", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid format! Use: deadline <description> /by <yyyy-MM-ddTHH:mm>");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();

        Task deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.showMessage("Got it. I've added this task:\n  " + deadline + "\nNow you have " + tasks.size() + " tasks in the list.");
        storage.saveTasks(tasks.getTasks());
    }
}
