package yapper.command;

import yapper.*;
import yapper.task.*;
import java.io.IOException;

/**
 * Represents a command to add a todo task to the task list.
 * Parses the user input to create a {@link Todo} and adds it to the {@link TaskList}.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Creates a new AddTodoCommand with the specified description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the AddTodoCommand by creating a {@link Todo} task,
     * adding it to the {@link TaskList}, displaying a confirmation message through {@link Ui},
     * and saving the updated task list to {@link Storage}.
     *
     * @param tasks   The {@link TaskList} to add the new task to.
     * @param ui      The {@link Ui} to display messages to the user.
     * @param storage The {@link Storage} to save the updated task list.
     * @throws IOException              If an error occurs while saving the task list to storage.
     * @throws IllegalArgumentException If the description is empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        Task todo = new Todo(description);
        tasks.addTask(todo);
        ui.showMessage("Got it. I've added this task:\n  " + todo + "\nNow you have " + tasks.size() + " tasks in the list.");
        storage.saveTasks(tasks.getTasks());
    }
}
