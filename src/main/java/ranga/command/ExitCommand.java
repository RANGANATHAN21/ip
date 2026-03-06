package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.ui.UI;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}