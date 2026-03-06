package ranga.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts at a specific date/time and ends at another.
 */
public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a new Event task with the given description, start and end date/time.
     *
     * @param description The description of the task
     * @param from        The start date/time string in yyyy-MM-dd HHmm format
     * @param to          The end date/time string in yyyy-MM-dd HHmm format
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    /**
     * Returns the start date/time as a string in the input format yyyy-MM-dd HHmm.
     *
     * @return The start date/time string
     */
    public String getFrom() {
        return from.format(INPUT_FORMAT);
    }

    /**
     * Returns the end date/time as a string in the input format yyyy-MM-dd HHmm.
     *
     * @return The end date/time string
     */
    public String getTo() {
        return to.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(OUTPUT_FORMAT)
                + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }
}