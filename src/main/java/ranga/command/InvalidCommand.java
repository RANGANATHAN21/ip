package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.ui.UI;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RangaException {
        throw new RangaException("Oi!! Get yourself together son!");
    }
}