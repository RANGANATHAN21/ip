package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.ui.UI;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTaskList(tasks.getTasks(), tasks.getTaskCount());
    }
}