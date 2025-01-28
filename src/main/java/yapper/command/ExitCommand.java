package yapper.command;

import yapper.*;
import yapper.task.*;

/**
 * Represents a command to exit the application.
 * Displays a goodbye message and signals the application to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand by displaying a goodbye message using {@link Ui}.
     *
     * @param tasks   The {@link TaskList}, not used in this command.
     * @param ui      The {@link Ui} to display the goodbye message.
     * @param storage The {@link Storage}, not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that this command causes the application to exit.
     *
     * @return True, as this command signals the termination of the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
