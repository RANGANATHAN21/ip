package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.ui.UI;

/**
 * Command that displays all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Displays all tasks in the task list.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTaskList(tasks.getTasks(), tasks.getTaskCount());
    }
}