package ranga.task;

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

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the status icon representing completion state.
     *
     * @return "X" if done, " " otherwise
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}