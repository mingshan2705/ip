import java.util.Scanner;

public class Yapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print welcome message
        printMessage("Hello! I'm Yapper!\n Yapa Yapa Yapa");

        while (true) {
            // Read user input
            String userInput = scanner.nextLine();

            // Check if the command is "bye"
            if (userInput.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                break; // Exit the loop
            }

            // Echo the user's input
            printMessage(userInput);
        }

        scanner.close();
    }

    public static void printMessage(String message) {
        String border = "____________________________________________________________";
        System.out.println(border);
        System.out.println(" " + message);
        System.out.println(border);
        System.out.println(); // Blank line for better readability
    }
}
