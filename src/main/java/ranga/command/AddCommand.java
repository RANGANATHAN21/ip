package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.task.Task;
import ranga.ui.UI;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getTaskCount());
    }
}