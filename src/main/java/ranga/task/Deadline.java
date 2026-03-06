package ranga.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    protected LocalDateTime by;

    /**
     * Creates a new Deadline task with the given description and due date/time.
     *
     * @param description The description of the task
     * @param by The due date/time string in yyyy-MM-dd HHmm format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    /**
     * Returns the due date/time as a string in the input format yyyy-MM-dd HHmm.
     *
     * @return The due date/time string
     */
    public String getBy() {
        return by.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}