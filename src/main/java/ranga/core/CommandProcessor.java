package ranga.core;

import ranga.exception.RangaException;
import ranga.task.Task;
import ranga.ui.UI;

/**
 * Handles processing of user commands.
 * Extracted from Ranga to reduce class length.
 */
public class CommandProcessor {

    /**
     * Processes user commands and delegates to appropriate handler methods.
     *
     * @param userInput The command string entered by the user
     * @param tasks The TaskList object
     * @param ui The UI object
     * @return false if user wants to exit (bye command), true otherwise
     * @throws RangaException if there is an error processing the command
     */
    public static boolean process(String userInput, TaskList tasks, UI ui)
            throws RangaException {

        if (userInput.equals("bye")) {
            ui.showGoodbye();
            return false;

        } else if (userInput.isEmpty()) {
            ui.showEmptyInputMessage();

        } else if (userInput.equals("list")) {
            ui.showTaskList(tasks.getTasks(), tasks.getTaskCount());

        } else if (userInput.startsWith("mark ")) {
            int index = Parser.parseTaskIndex(
                    userInput,
                    Parser.getMarkCommandOffset(),
                    tasks.getTaskCount()
            );
            tasks.markTask(index);
            ui.showTaskMarked(tasks.getTask(index));

        } else if (userInput.startsWith("unmark ")) {
            int index = Parser.parseTaskIndex(
                    userInput,
                    Parser.getUnmarkCommandOffset(),
                    tasks.getTaskCount()
            );
            tasks.unmarkTask(index);
            ui.showTaskUnmarked(tasks.getTask(index));

        } else if (userInput.startsWith("todo ")) {
            Task task = Parser.parseTodo(userInput);
            tasks.addTask(task);
            ui.showTaskAdded(task, tasks.getTaskCount());

        } else if (userInput.startsWith("deadline ")) {
            Task task = Parser.parseDeadline(userInput);
            tasks.addTask(task);
            ui.showTaskAdded(task, tasks.getTaskCount());

        } else if (userInput.startsWith("event ")) {
            Task task = Parser.parseEvent(userInput);
            tasks.addTask(task);
            ui.showTaskAdded(task, tasks.getTaskCount());

        } else {
            throw new RangaException("Oi!! Get yourself together son!");
        }

        return true;
    }
}