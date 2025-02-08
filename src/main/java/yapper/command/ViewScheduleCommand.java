package yapper.command;

import yapper.Storage;
import yapper.Ui;
import yapper.task.Task;
import yapper.task.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents a command to view tasks scheduled for a specific date.
 */
public class ViewScheduleCommand extends Command {
    private final LocalDate date;

    /**
     * Constructs a ViewScheduleCommand.
     *
     * @param date The date to filter tasks.
     */
    public ViewScheduleCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> scheduledTasks = tasks.getTasksForDate(date);

        if (scheduledTasks.isEmpty()) {
            ui.showMessage("No tasks scheduled for " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ".");
        } else {
            StringBuilder scheduleMessage = new StringBuilder("Here are your tasks for " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
            for (int i = 0; i < scheduledTasks.size(); i++) {
                scheduleMessage.append("\n ").append(i + 1).append(". ").append(scheduledTasks.get(i));
            }
            ui.showMessage(scheduleMessage.toString());
        }
    }
}
