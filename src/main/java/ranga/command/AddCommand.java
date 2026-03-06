package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.task.Task;
import ranga.ui.UI;

/**
 * Command that adds a task to the task list.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Creates an AddCommand with the task to add.
     *
     * @param task The task to add
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list and displays a confirmation message.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getTaskCount());
    }
}