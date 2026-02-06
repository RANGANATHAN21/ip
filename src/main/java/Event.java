/**
 * Represents a task that starts at a specific date/time and ends at another date/time.
 */
public class Event extends Task {

    // The start date/time of the event
    protected String from;
    // The end date/time of the event
    protected String to;

    /**
     * Creates a new Event task.
     *
     * @param description The description of the event
     * @param from The start date/time
     * @param to The end date/time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns string representation of the event task.
     *
     * @return Formatted string with type icon, task details, start and end times
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}