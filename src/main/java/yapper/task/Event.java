package yapper.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 * Extends the {@link Task} class to include additional details for event timing.
 */
public class Event extends Task {
    public LocalDateTime from;
    public LocalDateTime to;

    /**
     * Creates a new {@link Event} task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event in the format "yyyy-MM-ddTHH:mm".
     * @param to          The end time of the event in the format "yyyy-MM-ddTHH:mm".
     * @throws IllegalArgumentException If the input date/time strings are invalid or cannot be parsed.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
        validateDateTimeOrder(this.from, this.to);
        assert this.from.isBefore(this.to) : "Start time should be before end time";
    }

    /**
     * Parses a date-time string into a {@link LocalDateTime} object.
     *
     * @param dateTime The date-time string to parse.
     * @return The parsed {@link LocalDateTime} object.
     * @throws IllegalArgumentException If the input date/time string is invalid or cannot be parsed.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        assert dateTime != null : "Date/time string should not be null";
        try {
            return LocalDateTime.parse(dateTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date/time format: " + dateTime);
        }
    }

    /**
     * Validates that the start time is before the end time.
     *
     * @param from The start time.
     * @param to   The end time.
     * @throws IllegalArgumentException If the start time is not before the end time.
     */
    private void validateDateTimeOrder(LocalDateTime from, LocalDateTime to) {
        assert from != null : "Start time should not be null";
        assert to != null : "End time should not be null";
        if (!from.isBefore(to)) {
            throw new IllegalArgumentException("Start time should be before end time");
        }
    }

    /**
     * Returns a string representation of the event task.
     * Includes the task type, completion status, description, start time, and end time.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
