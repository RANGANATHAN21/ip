package ranga.command;

import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.task.Task;
import ranga.ui.UI;
import java.util.ArrayList;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RangaException {
        ArrayList<Task> matching = tasks.findTasks(keyword);
        ui.showMatchingTasks(matching);
    }
}