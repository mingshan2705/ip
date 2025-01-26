import java.io.IOException;

public class MarkCommand extends Command {
    private final String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            Task markedTask = tasks.markTaskAsDone(index);
            ui.showMessage("Nice! I've marked this task as done:\n  " + markedTask);
            storage.saveTasks(tasks.getTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid task number.");
        }
    }
}
