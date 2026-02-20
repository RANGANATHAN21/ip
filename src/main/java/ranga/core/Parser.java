package ranga.core;

import ranga.task.Deadline;
import ranga.task.Event;
import ranga.exception.RangaException;
import ranga.task.Task;
import ranga.task.Todo;

/**
 * Parses user input and extracts command information.
 * Validates input format and throws appropriate exceptions for invalid input.
 */
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
     * Parses a todo command and creates a Todo task.
     *
     * @param userInput The full user input string
     * @return A new Todo task
     * @throws RangaException if the description is empty
     */
    public static Task parseTodo(String userInput) throws RangaException {
        String description = userInput.substring(TODO_COMMAND_OFFSET).trim();

        if (description.isEmpty()) {
            throw new RangaException("Blank tasks are how people explode.");
        }

        return new Todo(description);
    }

    /**
     * Parses a deadline command and creates a Deadline task.
     *
     * @param userInput The full user input string
     * @return A new Deadline task
     * @throws RangaException if format is invalid or fields are empty
     */
    public static Task parseDeadline(String userInput) throws RangaException {
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

    /**
     * Parses an event command and creates an Event task.
     *
     * @param userInput The full user input string
     * @return A new Event task
     * @throws RangaException if format is invalid or fields are empty
     */
    public static Task parseEvent(String userInput) throws RangaException {
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

    /**
     * Parses a task index from a mark or unmark command.
     *
     * @param userInput The full user input string
     * @param offset The offset to start parsing from
     * @param maxTaskCount The maximum valid task index
     * @return The parsed task index (0-based)
     * @throws RangaException if the index is invalid
     */
    public static int parseTaskIndex(String userInput, int offset, int maxTaskCount) throws RangaException {
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

        if (index >= maxTaskCount) {
            throw new RangaException("Tek Knight couldn't find that task. Keep trolling and we'll wire $1M from your account to BLM.");
        }

        return index;
    }

    /**
     * Gets the mark command offset.
     *
     * @return The mark command offset
     */
    public static int getMarkCommandOffset() {
        return MARK_COMMAND_OFFSET;
    }

    /**
     * Gets the unmark command offset.
     *
     * @return The unmark command offset
     */
    public static int getUnmarkCommandOffset() {
        return UNMARK_COMMAND_OFFSET;
    }

    /**
     * Gets the delete command offset.
     *
     * @return The delete command offset
     */
    public static int getDeleteCommandOffset() {
        return DELETE_COMMAND_OFFSET;
    }
}