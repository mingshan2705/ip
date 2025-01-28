package yapper;

import yapper.command.*;

public class Parser {

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
