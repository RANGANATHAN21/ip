/**
 * Manages the list of tasks for the Ranga application.
 * Handles adding, retrieving, and querying tasks.
 */
public class TaskList {

    private static final int MAX_TASKS = 100;
    private final Task[] tasks;
    private int taskCount;

    /**
     * Creates a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new Task[MAX_TASKS];
        this.taskCount = 0;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add
     * @throws RangaException if the task list is full
     */
    public void addTask(Task task) throws RangaException {
        if (taskCount >= MAX_TASKS) {
            throw new RangaException("Memory full. This is why we can't have nice things.");
        }

        tasks[taskCount] = task;
        taskCount++;
    }

    /**
     * Gets a task at the specified index.
     *
     * @param index The index of the task (0-based)
     * @return The task at the specified index
     * @throws RangaException if index is out of bounds
     */
    public Task getTask(int index) throws RangaException {
        if (index < 0 || index >= taskCount) {
            throw new RangaException("Tek Knight couldn't find that task. Keep trolling and we'll wire $1M from your account to BLM.");
        }
        return tasks[index];
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark (0-based)
     * @throws RangaException if index is out of bounds
     */
    public void markTask(int index) throws RangaException {
        Task task = getTask(index);
        task.markAsDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to unmark (0-based)
     * @throws RangaException if index is out of bounds
     */
    public void unmarkTask(int index) throws RangaException {
        Task task = getTask(index);
        task.markAsNotDone();
    }

    /**
     * Gets the current number of tasks in the list.
     *
     * @return The number of tasks
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Gets the array of tasks.
     *
     * @return The tasks array
     */
    public Task[] getTasks() {
        return tasks;
    }
}