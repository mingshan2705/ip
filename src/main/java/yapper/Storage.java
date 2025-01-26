package yapper;

import yapper.task.Deadline;
import yapper.task.Event;
import yapper.task.Task;
import yapper.task.Todo;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    // Load tasks from the file
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        // Create file and parent directories if they don't exist
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            tasks.add(parseTask(line));
        }
        reader.close();
        return tasks;
    }

    // Save tasks to the file
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (Task task : tasks) {
            writer.write(serializeTask(task));
            writer.newLine();
        }
        writer.close();
    }

    // Parse a line into a yapper.task.Task object
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case "T": // yapper.task.Todo
                Todo todo = new Todo(description);
                if (isDone) todo.markAsDone();
                return todo;

            case "D": // yapper.task.Deadline
                Deadline deadline = new Deadline(description, parts[3]); // parts[3] is the date-time
                if (isDone) deadline.markAsDone();
                return deadline;

            case "E": // yapper.task.Event
                Event event = new Event(description, parts[3], parts[4]); // parts[3] is 'from', parts[4] is 'to'
                if (isDone) event.markAsDone();
                return event;

            default:
                throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }

    // Serialize a yapper.task.Task object into a line
    private String serializeTask(Task task) {
        String taskType = task instanceof Todo ? "T"
                : task instanceof Deadline ? "D"
                : "E";
        String status = task.isDone ? "1" : "0";

        if (task instanceof Todo) {
            return String.join(" | ", taskType, status, task.description);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.join(" | ", taskType, status, deadline.description, deadline.by.toString());
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.join(" | ", taskType, status, event.description, event.from.toString(), event.to.toString());
        }
        throw new IllegalArgumentException("Unknown task type: " + task.getClass().getSimpleName());
    }
}
