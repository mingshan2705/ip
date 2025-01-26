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

    // Parse a line into a Task object
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) todo.markAsDone();
                return todo;
            case "D":
                Deadline deadline = new Deadline(description, parts[3]);
                if (isDone) deadline.markAsDone();
                return deadline;
            case "E":
                Event event = new Event(description, parts[3], parts[4]);
                if (isDone) event.markAsDone();
                return event;
            default:
                throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }

    // Serialize a Task object into a line
    private String serializeTask(Task task) {
        String taskType = task instanceof Todo ? "T"
                : task instanceof Deadline ? "D"
                : "E";
        String status = task.isDone ? "1" : "0";

        if (task instanceof Todo) {
            return String.join(" | ", taskType, status, task.description);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.join(" | ", taskType, status, deadline.description, ((Deadline) task).by);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.join(" | ", taskType, status, event.description, event.from, event.to);
        }
        throw new IllegalArgumentException("Unknown task type: " + task.getClass().getSimpleName());
    }
}
