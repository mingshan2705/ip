package yapper.command;

import yapper.*;
import yapper.task.*;
import java.io.IOException;

public class DeleteCommand extends Command {
    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            Task removedTask = tasks.deleteTask(index);
            ui.showMessage("Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.");
            storage.saveTasks(tasks.getTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid task number.");
        }
    }
}
