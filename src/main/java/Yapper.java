import java.util.Scanner;

public class Yapper {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print welcome message
        printMessage("Hello! I'm Yapper!\nYapa Yapa Yapa");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                displayTasks();
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
                    addDeadline(parts[0].trim(), parts[1].trim());
                } else {
                    printMessage("Invalid format! Use: deadline <description> /by <time>");
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

    public static void addTask(Task task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            printMessage("Got it. I've added this task:\n  " + task + "\nNow you have " + taskCount + " tasks in the list.");
        } else {
            printMessage("Sorry, I can't store more tasks.");
        }
    }

    public static void addTodo(String description) {
        Task todo = new Todo(description);
        addTask(todo);
    }

    public static void addDeadline(String description, String by) {
        Task deadline = new Deadline(description, by);
        addTask(deadline);
    }

    public static void addEvent(String description, String from, String to) {
        Task event = new Event(description, from, to);
        addTask(event);
    }

    public static void displayTasks() {
        if (taskCount == 0) {
            printMessage("No tasks added yet.");
        } else {
            StringBuilder message = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                message.append("\n ").append(i + 1).append(".").append(tasks[i]);
            }
            printMessage(message.toString());
        }
    }

    public static void markTaskAsDone(int index) {
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsDone();
            printMessage("Nice! I've marked this task as done:\n  " + tasks[index]);
        } else {
            printMessage("Invalid task number!");
        }
    }

    public static void markTaskAsNotDone(int index) {
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsNotDone();
            printMessage("OK, I've marked this task as not done yet:\n  " + tasks[index]);
        } else {
            printMessage("Invalid task number!");
        }
    }
}
