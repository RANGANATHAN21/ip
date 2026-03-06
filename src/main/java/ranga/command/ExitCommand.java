package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.ui.UI;

/**
 * Command that exits the application.
 */
public class ExitCommand extends Command {

    /**
     * Displays the goodbye message.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns true, indicating the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}