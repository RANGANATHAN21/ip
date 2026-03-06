package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.ui.UI;

/**
 * Abstract base class representing a user command.
 * Each subclass encapsulates the logic for a specific command.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks The task list to operate on
     * @param ui The UI to display output
     * @param storage The storage to persist changes
     * @throws RangaException if there is an error executing the command
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws RangaException;

    /**
     * Returns whether this command signals the application to exit.
     *
     * @return true if the app should exit, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}