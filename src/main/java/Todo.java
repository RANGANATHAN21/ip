/**
 * Represents a todo task without any date/time attached.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task.
     *
     * @param description The description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation of the todo.
     *
     * @return Formatted string with type icon and task details
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}