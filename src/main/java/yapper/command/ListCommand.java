package yapper.command;

import yapper.*;
import yapper.task.*;

/**
 * Represents a command to list all tasks in the task list.
 * Displays the current tasks to the user through the {@link Ui}.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by displaying all tasks in the {@link TaskList}.
     * If the task list is empty, a message indicating no tasks are present is shown.
     *
     * @param tasks   The {@link TaskList} containing the tasks to be displayed.
     * @param ui      The {@link Ui} to display messages to the user.
     * @param storage The {@link Storage}, not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.showMessage("No tasks added yet.");
        } else {
            StringBuilder message = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                message.append("\n ").append(i + 1).append(".").append(tasks.getTasks().get(i));
            }
            ui.showMessage(message.toString());
        }
    }
}
