package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.ui.UI;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws RangaException;

    public boolean isExit() {
        return false;
    }
}