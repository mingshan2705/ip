package yapper.task;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("yapper.task.Task number is invalid.");
        }
        return tasks.remove(index);
    }

    public Task markTaskAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("yapper.task.Task number is invalid.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task markTaskAsNotDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("yapper.task.Task number is invalid.");
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}
