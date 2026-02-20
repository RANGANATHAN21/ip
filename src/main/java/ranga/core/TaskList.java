package ranga.core;

import ranga.exception.RangaException;
import ranga.task.Task;
import java.util.ArrayList;

/**
 * Manages the list of tasks for the Ranga application.
 * Handles adding, retrieving, querying and deleting tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets a task at the specified index.
     *
     * @param index The index of the task (0-based)
     * @return The task at the specified index
     * @throws RangaException if index is out of bounds
     */
    public Task getTask(int index) throws RangaException {
        if (index < 0 || index >= tasks.size()) {
            throw new RangaException("Tek Knight couldn't find that task. Keep trolling and we'll wire $1M from your account to BLM.");
        }
        return tasks.get(index);
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task (0-based)
     * @return The task at the specified index
     * @throws RangaException if index is out of bounds
     */
    public Task deleteTask(int index) throws RangaException {
        if (index < 0 || index >= tasks.size()) {
            throw new RangaException("Tek Knight couldn't find that task. Keep trolling and we'll wire $1M from your account to BLM.");
        }
        return tasks.remove(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark (0-based)
     * @throws RangaException if index is out of bounds
     */
    public void markTask(int index) throws RangaException {
        getTask(index).markAsDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to unmark (0-based)
     * @throws RangaException if index is out of bounds
     */
    public void unmarkTask(int index) throws RangaException {
        getTask(index).markAsNotDone();
    }

    /**
     * Gets the current number of tasks in the list.
     *
     * @return The number of tasks
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Gets the ArrayList of tasks.
     *
     * @return The tasks ArrayList
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}