package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.ui.UI;

/**
 * Command that marks a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Creates an UnmarkCommand for the task at the given index.
     *
     * @param index The 0-based index of the task to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the stored index as not done and displays a confirmation message.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RangaException {
        tasks.unmarkTask(index);
        ui.showTaskUnmarked(tasks.getTask(index));
    }
}