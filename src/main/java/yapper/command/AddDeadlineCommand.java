package yapper.command;

import yapper.*;
import yapper.task.*;
import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private final String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

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
