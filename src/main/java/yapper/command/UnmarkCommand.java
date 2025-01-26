package yapper.command;

import yapper.*;
import yapper.task.*;
import java.io.IOException;

public class UnmarkCommand extends Command {
    private final String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            Task unmarkedTask = tasks.markTaskAsNotDone(index);
            ui.showMessage("OK, I've marked this task as not done yet:\n  " + unmarkedTask);
            storage.saveTasks(tasks.getTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid task number.");
        }
    }
}
