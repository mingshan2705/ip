package yapper;

import yapper.command.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user input into commands.
 */
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
            case "schedule":
                return parseScheduleCommand(arguments);
            default:
                throw new IllegalArgumentException("I'm sorry, I don't understand that command.");
        }
    }

    /**
     * Parses the schedule command to extract a date.
     *
     * @param arguments The user input containing the date.
     * @return A ViewScheduleCommand if a valid date is provided.
     */
    private Command parseScheduleCommand(String arguments) {
        try {
            LocalDate date = LocalDate.parse(arguments.trim()); // Accepts YYYY-MM-DD format
            return new ViewScheduleCommand(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use YYYY-MM-DD.");
        }
    }
}
