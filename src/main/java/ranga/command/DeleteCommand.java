package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.task.Task;
import ranga.ui.UI;

/**
 * Command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Creates a DeleteCommand for the task at the given index.
     *
     * @param index The 0-based index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the stored index and displays a confirmation message.
     *
     * @throws RangaException if the index is out of bounds
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RangaException {
        Task deleted = tasks.deleteTask(index);
        ui.showTaskDeleted(deleted, tasks.getTaskCount());
    }
}