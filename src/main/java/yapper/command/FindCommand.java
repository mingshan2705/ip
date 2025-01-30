package yapper.command;

import yapper.Storage;
import yapper.Ui;
import yapper.task.Task;
import yapper.task.TaskList;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        // Find tasks that contain the keyword in their description
        List<Task> matchingTasks = tasks.getTasks()
                .stream()
                .filter(task -> task.description.contains(keyword))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                message.append("\n ").append(i + 1).append(".").append(matchingTasks.get(i));
            }
            ui.showMessage(message.toString());
        }
    }
}
