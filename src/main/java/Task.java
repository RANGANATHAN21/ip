/**
 * Base class representing a task with a description and completion status.
 * Subclasses should implement specific task types (Todo, Deadline, Event).
 */
public class Task {

    private final String description;
    private boolean isDone;

    /**
     * Creates a new task with the given description.
     * Task is initially marked as not done.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}