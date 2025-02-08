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
        this.from = LocalDateTime.parse(from); // Parse start datetime
        this.to = LocalDateTime.parse(to);     // Parse end datetime
        // Assert that the start time is before the end time
        assert this.from.isBefore(this.to) : "Start time should be before end time";
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
