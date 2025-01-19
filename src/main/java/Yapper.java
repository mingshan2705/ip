import java.util.Scanner;

public class Yapper {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print welcome message
        printMessage("Hello! I'm Yapper!\nYapa Yapa Yapa");

        while (true) {
            // Read user input
            String userInput = scanner.nextLine();

            // Check if the command is "bye"
            if (userInput.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                break; // Exit the loop
            } else if (userInput.equals("list")) {
                displayTasks();
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    public static void printMessage(String message) {
        String border = "____________________________________________________________";
        System.out.println(border);
        System.out.println(message);
        System.out.println(border);
        System.out.println(); // Blank line for better readability
    }

    public static void addTask(String task) {
        if (taskCount >= MAX_TASKS) {
            printMessage("You have reached the maximum number of tasks!");
        } else {
            tasks[taskCount++] = task;
            printMessage("Added " + task);
        }
    }

    public static void displayTasks() {
        if (taskCount == 0) {
            printMessage("No tasks added yet.");
        } else {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < taskCount; i++) {
                message.append((i + 1)).append(". ").append(tasks[i]).append("\n");
            }
            printMessage(message.toString().trim());
        }
    }
}
