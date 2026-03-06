package ranga.command;

import java.util.ArrayList;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.task.Task;
import ranga.ui.UI;

/**
 * Command that searches for tasks matching a keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a FindCommand with the given search keyword.
     *
     * @param keyword The keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all tasks whose descriptions contain the keyword and displays them.
     *
     * @throws RangaException if an error occurs during task lookup
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RangaException {
        ArrayList<Task> matching = tasks.findTasks(keyword);
        ui.showMatchingTasks(matching);
    }
}