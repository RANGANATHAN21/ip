package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.task.Task;
import ranga.ui.UI;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RangaException {
        Task deleted = tasks.deleteTask(index);
        ui.showTaskDeleted(deleted, tasks.getTaskCount());
    }
}