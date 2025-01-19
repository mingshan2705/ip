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
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                markTaskAsDone(taskIndex);
            } else if (userInput.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                markTaskAsNotDone(taskIndex);
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    public static void printMessage(String message) {
        String border = "____________________________________________________________";
        System.out.println(border);
        System.out.println(" " + message);
        System.out.println(border);
        System.out.println(); // Blank line for readability
    }

    public static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Task(taskDescription);
            taskCount++;
            printMessage("added: " + taskDescription);
        } else {
            printMessage("Sorry, I can't store more tasks.");
        }
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
