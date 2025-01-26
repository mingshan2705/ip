package yapper.command;

import yapper.*;
import yapper.task.*;
import java.io.IOException;

public class AddEventCommand extends Command {
    private final String input;

    public AddEventCommand(String input) {
        this.input = input;
    }

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
