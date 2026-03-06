package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.ui.UI;

/**
 * Command that represents an unrecognised user input.
 * Always throws a RangaException when executed.
 */
public class InvalidCommand extends Command {

    /**
     * Throws a RangaException to indicate the command was not recognised.
     *
     * @throws RangaException always
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RangaException {
        throw new RangaException("Oi!! Get yourself together son!");
    }
}