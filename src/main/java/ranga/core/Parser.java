package ranga.core;

import java.time.format.DateTimeParseException;

import ranga.command.AddCommand;
import ranga.command.Command;
import ranga.command.DeleteCommand;
import ranga.command.ExitCommand;
import ranga.command.FindCommand;
import ranga.command.InvalidCommand;
import ranga.command.ListCommand;
import ranga.command.MarkCommand;
import ranga.command.UnmarkCommand;
import ranga.exception.RangaException;
import ranga.task.Deadline;
import ranga.task.Event;
import ranga.task.Task;
import ranga.task.Todo;

/**
 * Parses raw user input into executable Command objects.
 * Also handles validation of input format and throws appropriate exceptions.
 */
public class Parser {

    private static final int TODO_OFFSET = 5;
    private static final int DEADLINE_OFFSET = 9;
    private static final int EVENT_OFFSET = 6;
    private static final int MARK_OFFSET = 5;
    private static final int UNMARK_OFFSET = 7;
    private static final int DELETE_OFFSET = 7;
    private static final int FIND_OFFSET = 5;

    private static final int BY_TAG_LENGTH = 3;
    private static final int FROM_TAG_LENGTH = 5;
    private static final int TO_TAG_LENGTH = 3;

    /**
     * Parses a user input string and returns the corresponding Command.
     *
     * @param userInput The raw input string from the user
     * @return The Command corresponding to the user input
     * @throws RangaException if the input format is invalid
     */
    public static Command parse(String userInput) throws RangaException {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("mark ")) {
            return new MarkCommand(parseIndex(userInput, MARK_OFFSET));
        } else if (userInput.startsWith("unmark ")) {
            return new UnmarkCommand(parseIndex(userInput, UNMARK_OFFSET));
        } else if (userInput.startsWith("delete ")) {
            return new DeleteCommand(parseIndex(userInput, DELETE_OFFSET));
        } else if (userInput.startsWith("todo ")) {
            return new AddCommand(parseTodo(userInput));
        } else if (userInput.startsWith("deadline ")) {
            return new AddCommand(parseDeadline(userInput));
        } else if (userInput.startsWith("event ")) {
            return new AddCommand(parseEvent(userInput));
        } else if (userInput.startsWith("find ")) {
            return parseFindCommand(userInput);
        } else {
            return new InvalidCommand();
        }
    }

    private static Task parseTodo(String userInput) throws RangaException {
        String description = userInput.substring(TODO_OFFSET).trim();
        if (description.isEmpty()) {
            throw new RangaException("Blank tasks are how people explode.");
        }
        return new Todo(description);
    }

    private static Task parseDeadline(String userInput) throws RangaException {
        String details = userInput.substring(DEADLINE_OFFSET).trim();
        int byIndex = details.indexOf("/by");
        if (byIndex == -1) {
            throw new RangaException(
                    "Strictly adhere to Vought guidelines: deadline DESCRIPTION /by yyyy-MM-dd HHmm");
        }
        String description = details.substring(0, byIndex).trim();
        String by = details.substring(byIndex + BY_TAG_LENGTH).trim();
        if (description.isEmpty()) {
            throw new RangaException("Oi. Description. Try again.");
        }
        if (by.isEmpty()) {
            throw new RangaException("Oi. Deadline. Try again.");
        }
        try {
            return new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new RangaException(
                    "Strictly adhere to Vought guidelines:: yyyy-MM-dd HHmm (eg. 2019-12-02 1800)");
        }
    }

    private static Task parseEvent(String userInput) throws RangaException {
        String details = userInput.substring(EVENT_OFFSET).trim();
        int fromIndex = details.indexOf("/from");
        int toIndex = details.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new RangaException("Strictly adhere to Vought guidelines: "
                    + "event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        String description = details.substring(0, fromIndex).trim();
        String from = details.substring(fromIndex + FROM_TAG_LENGTH, toIndex).trim();
        String to = details.substring(toIndex + TO_TAG_LENGTH).trim();
        if (description.isEmpty()) {
            throw new RangaException("Oi. Description. Try again.");
        }
        if (from.isEmpty()) {
            throw new RangaException("When was the event starting again...");
        }
        if (to.isEmpty()) {
            throw new RangaException("When was the event ending again...");
        }
        try {
            return new Event(description, from, to);
        } catch (DateTimeParseException e) {
            throw new RangaException(
                    "Strictly adhere to Vought guidelines: yyyy-MM-dd HHmm (eg. 2019-12-02 1800)");
        }
    }

    private static Command parseFindCommand(String userInput) throws RangaException {
        String keyword = userInput.substring(FIND_OFFSET).trim();
        if (keyword.isEmpty()) {
            throw new RangaException("Give me something to work with son.");
        }
        return new FindCommand(keyword);
    }

    private static int parseIndex(String userInput, int offset) throws RangaException {
        String indexString = userInput.substring(offset).trim();
        if (indexString.isEmpty()) {
            throw new RangaException("No task number given. Try again.");
        }
        int index;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new RangaException("That's not a number. Try again.");
        }
        if (index < 0) {
            throw new RangaException("Task number must be positive.");
        }
        return index;
    }
}