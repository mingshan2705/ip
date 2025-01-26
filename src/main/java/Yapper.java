import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Yapper {
    private static final String FILE_PATH = "./data/yapper.txt";
    private static ArrayList<Task> tasks;
    private static Storage storage;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        storage = new Storage(FILE_PATH);

        // Load tasks from file
        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            printMessage("Failed to load tasks. Starting with an empty list.");
            tasks = new ArrayList<>();
        }

        // Print welcome message
        printMessage("Hello! I'm Yapper!\nYapa Yapa Yapa");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                displayTasks();
            } else if (userInput.startsWith("delete ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    deleteTask(taskIndex);
                } catch (Exception e) {
                    printMessage("Invalid input. Use 'delete <task_number>'.");
                }
            } else if (userInput.startsWith("mark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    markTaskAsDone(taskIndex);
                } catch (Exception e) {
                    printMessage("Invalid input. Use 'mark <task_number>'.");
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    markTaskAsNotDone(taskIndex);
                } catch (Exception e) {
                    printMessage("Invalid input. Use 'unmark <task_number>'.");
                }
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(4).trim();
                if (description.isEmpty()) {
                    printMessage("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    addTodo(description);
                }
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ", 2);
                if (parts.length == 2) {
                    try {
                        addDeadline(parts[0].trim(), parts[1].trim()); // Ensure date-time is in yyyy-MM-ddTHH:mm format
                    } catch (Exception e) {
                        printMessage("Invalid date-time format! Use: deadline <description> /by <yyyy-MM-ddTHH:mm>");
                    }
                } else {
                    printMessage("Invalid format! Use: deadline <description> /by <yyyy-MM-ddTHH:mm>");
                }
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from ", 2);
                if (parts.length == 2 && parts[1].contains(" /to ")) {
                    String description = parts[0].trim();
                    String from = parts[1].split(" /to ")[0].trim();
                    String to = parts[1].split(" /to ")[1].trim();
                    addEvent(description, from, to);
                } else {
                    printMessage("Invalid format! Use: event <description> /from <start> /to <end>");
                }
            } else {
                printMessage("Unknown command!");
            }
        }

        scanner.close();
    }

    public static void printMessage(String message) {
        String border = "____________________________________________________________";
        System.out.println(border);
        System.out.println(message);
        System.out.println(border);
        System.out.println(); // Blank line for readability
    }

    private static void saveTasks() {
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            printMessage("Failed to save tasks to disk.");
        }
    }

    public static void addTask(Task task) {
        tasks.add(task);
        printMessage("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
        saveTasks();
    }

    public static void addTodo(String description) {
        Task todo = new Todo(description);
        addTask(todo);
    }

    public static void addDeadline(String description, String by) {
        try {
            Task deadline = new Deadline(description, by);
            addTask(deadline);
        } catch (Exception e) {
            printMessage("Invalid date-time format! Please use yyyy-MM-ddTHH:mm.");
        }
    }


    public static void addEvent(String description, String from, String to) {
        try {
            Task event = new Event(description, from, to);
            addTask(event);
        } catch (Exception e) {
            printMessage("Invalid datetime format! Please use yyyy-MM-ddTHH:mm.");
        }
    }

    public static void displayTasks() {
        if (tasks.isEmpty()) {
            printMessage("No tasks added yet.");
        } else {
            StringBuilder message = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                message.append("\n ").append(i + 1).append(".").append(tasks.get(i));
            }
            printMessage(message.toString());
        }
    }

    public static void markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            printMessage("Nice! I've marked this task as done:\n  " + tasks.get(index));
            saveTasks();
        } else {
            printMessage("Task number out of range. Please enter a valid task number.");
        }
    }

    public static void markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            printMessage("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
            saveTasks();
        } else {
            printMessage("Task number out of range. Please enter a valid task number.");
        }
    }

    public static void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            printMessage("Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.");
            saveTasks();
        } else {
            printMessage("Task number out of range. Please enter a valid task number.");
        }
    }
}
