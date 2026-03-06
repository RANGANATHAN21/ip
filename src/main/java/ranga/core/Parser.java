package ranga.core;

import ranga.command.*;
import ranga.exception.RangaException;
import ranga.task.Deadline;
import ranga.task.Event;
import ranga.task.Task;
import ranga.task.Todo;

public class Parser {

    private static final int TODO_COMMAND_OFFSET = 5;
    private static final int DEADLINE_COMMAND_OFFSET = 9;
    private static final int EVENT_COMMAND_OFFSET = 6;
    private static final int DELETE_COMMAND_OFFSET = 7;
    private static final int MARK_COMMAND_OFFSET = 5;
    private static final int UNMARK_COMMAND_OFFSET = 7;
    private static final int BY_TAG_LENGTH = 3;
    private static final int FROM_TAG_LENGTH = 5;
    private static final int TO_TAG_LENGTH = 3;

    /**
     * Parses user input and returns the appropriate Command object.
     *
     * @param userInput The full user input string
     * @return The corresponding Command object
     * @throws RangaException if input is invalid
     */
    public static Command parse(String userInput) throws RangaException {
        if (userInput.equals("bye")) {
            return new ExitCommand();

        } else if (userInput.equals("list")) {
            return new ListCommand();

        } else if (userInput.startsWith("mark ")) {
            int index = parseTaskIndex(userInput, MARK_COMMAND_OFFSET);
            return new MarkCommand(index);

        } else if (userInput.startsWith("unmark ")) {
            int index = parseTaskIndex(userInput, UNMARK_COMMAND_OFFSET);
            return new UnmarkCommand(index);

        } else if (userInput.startsWith("todo ")) {
            Task task = parseTodo(userInput);
            return new AddCommand(task);

        } else if (userInput.startsWith("deadline ")) {
            Task task = parseDeadline(userInput);
            return new AddCommand(task);

        } else if (userInput.startsWith("event ")) {
            Task task = parseEvent(userInput);
            return new AddCommand(task);

        } else if (userInput.startsWith("delete ")) {
            int index = parseTaskIndex(userInput, DELETE_COMMAND_OFFSET);
            return new DeleteCommand(index);

        } else {
            return new InvalidCommand();
        }
    }

    private static Task parseTodo(String userInput) throws RangaException {
        String description = userInput.substring(TODO_COMMAND_OFFSET).trim();
        if (description.isEmpty()) {
            throw new RangaException("Blank tasks are how people explode.");
        }
        return new Todo(description);
    }

    private static Task parseDeadline(String userInput) throws RangaException {
        String details = userInput.substring(DEADLINE_COMMAND_OFFSET).trim();
        int byIndex = details.indexOf("/by");
        if (byIndex == -1) {
            throw new RangaException("Strictly adhere to Vought guidelines: deadline DESCRIPTION /by DEADLINE");
        }
        String description = details.substring(0, byIndex).trim();
        String by = details.substring(byIndex + BY_TAG_LENGTH).trim();
        if (description.isEmpty()) {
            throw new RangaException("Oi. Description. Try again.");
        }
        if (by.isEmpty()) {
            throw new RangaException("Oi. Deadline. Try again.");
        }
        return new Deadline(description, by);
    }

    private static Task parseEvent(String userInput) throws RangaException {
        String details = userInput.substring(EVENT_COMMAND_OFFSET).trim();
        int fromIndex = details.indexOf("/from");
        int toIndex = details.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new RangaException("Strictly adhere to Vought guidelines: event DESCRIPTION /from START /to END");
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
        return new Event(description, from, to);
    }

    private static int parseTaskIndex(String userInput, int offset) throws RangaException {
        String indexString = userInput.substring(offset).trim();
        if (indexString.isEmpty()) {
            throw new RangaException("Tek Knight couldn't find that task. Keep trolling and we'll wire $1M from your account to BLM.");
        }
        int index;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new RangaException("Black Noir doesn't have time to teach numbers. Last chance.");
        }
        if (index < 0) {
            throw new RangaException("Negative tasks? Really?");
        }
        return index;
    }
}