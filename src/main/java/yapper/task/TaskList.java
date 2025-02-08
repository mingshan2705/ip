package yapper.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    public final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks); // Ensure correct type
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task number is invalid.");
        }
        return tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task markTaskAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task number is invalid.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task markTaskAsNotDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task number is invalid.");
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Returns tasks scheduled for a specific date.
     *
     * @param date The date to filter tasks.
     * @return A list of tasks scheduled for that date.
     */
    public List<Task> getTasksForDate(LocalDate date) {
        List<Task> scheduledTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                if (((Deadline) task).by.toLocalDate().equals(date)) {
                    scheduledTasks.add(task);
                }
            } else if (task instanceof Event) {
                if (((Event) task).from.toLocalDate().equals(date) || ((Event) task).to.toLocalDate().equals(date)) {
                    scheduledTasks.add(task);
                }
            }
        }
        return scheduledTasks;
    }
}
