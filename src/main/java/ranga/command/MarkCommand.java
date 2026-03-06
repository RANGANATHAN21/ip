package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.ui.UI;

/**
 * Command that marks a task as done.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Creates a MarkCommand for the task at the given index.
     *
     * @param index The 0-based index of the task to mark
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the stored index as done and displays a confirmation message.
     *
     * @throws RangaException if the index is out of bounds
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RangaException {
        tasks.markTask(index);
        ui.showTaskMarked(tasks.getTask(index));
    }
}