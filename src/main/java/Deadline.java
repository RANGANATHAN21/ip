/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    // The deadline by which the task must be completed
    protected String by;

    /**
     * Creates a new Deadline task.
     *
     * @param description The description of the task
     * @param by The deadline date/time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns string representation of the deadline task.
     *
     * @return Formatted string with type icon, task details, and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}