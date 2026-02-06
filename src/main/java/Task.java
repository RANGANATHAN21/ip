/**
 * Base class representing a task with a description and completion status.
 * Subclasses should implement specific task types (Todo, Deadline, Event).
 */
public class Task {

    // The description text of the task
    private final String description;
    // Whether the task has been completed
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

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the status icon for this task.
     *
     * @return "X" if done, " " (space) if not done
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns a string representation of the task including type icon and status.
     * Subclasses should override to add type-specific information.
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}