package yapper.task;

/**
 * Represents a todo task.
 * A {@link Todo} is a basic task with only a description and no specific date or time associated with it.
 * Extends the {@link Task} class.
 */
public class Todo extends Task {

    /**
     * Creates a new {@link Todo} task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     * Includes the task type and completion status.
     *
     * @return A formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
