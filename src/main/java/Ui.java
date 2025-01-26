import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showMessage("Hello! I'm Yapper!\nYapa Yapa Yapa");
    }

    public void showGoodbye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        String border = "____________________________________________________________";
        System.out.println(border);
        System.out.println(message);
        System.out.println(border);
        System.out.println(); // Blank line for readability
    }

    public void showLoadingError() {
        showMessage("Failed to load tasks. Starting with an empty list.");
    }

    public void showError(String errorMessage) {
        showMessage("OOPS!!! " + errorMessage);
    }
}
