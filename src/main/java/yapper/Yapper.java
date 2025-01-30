package yapper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yapper.ui.YapperController;
import yapper.task.TaskList;
import yapper.command.Command;
import yapper.command.ExitCommand;

import java.io.IOException;
import java.util.Scanner;

/**
 * Yapper Chatbot with JavaFX UI.
 */
public class Yapper extends Application {
    private static final String FILE_PATH = "./data/yapper.txt";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Launches the chatbot UI.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        storage = new Storage(FILE_PATH);
        ui = new Ui();
        parser = new Parser();

        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showMessage("Error loading tasks. Starting with an empty list.");
            taskList = new TaskList();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Yapper.fxml"));
        AnchorPane root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Yapper - Smart Task Assistant");
        stage.show();

        YapperController controller = fxmlLoader.getController();
        controller.setYapper(this);
    }

    /**
     * Processes user input and returns Yapper's response.
     *
     * @param input The user command input.
     * @return The chatbot response message.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            command.execute(taskList, ui, storage);

            if (command instanceof ExitCommand) {
                System.exit(0);
            }

            return ui.getLastMessage(); // ✅ Show actual chatbot message instead of a static response
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
