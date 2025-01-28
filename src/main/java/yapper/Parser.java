package yapper;

import yapper.command.*;

/**
 * Parses user input and returns the appropriate {@link Command} to execute.
 */
public class Parser {

    /**
     * Parses the full command input from the user and returns the corresponding {@link Command}.
     *
     * @param fullCommand The full command input from the user.
     * @return A {@link Command} object that represents the user command.
     * @throws IllegalArgumentException If the command is not recognized.
     */
    public Command parse(String fullCommand) {
        String[] split = fullCommand.split(" ", 2);
        String commandWord = split[0];
        String arguments = split.length > 1 ? split[1] : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return new AddTodoCommand(arguments);
            case "deadline":
                return new AddDeadlineCommand(arguments);
            case "event":
                return new AddEventCommand(arguments);
            case "delete":
                return new DeleteCommand(arguments);
            case "mark":
                return new MarkCommand(arguments);
            case "unmark":
                return new UnmarkCommand(arguments);
            default:
                throw new IllegalArgumentException("I'm sorry, I don't understand that command.");
        }
    }
}
