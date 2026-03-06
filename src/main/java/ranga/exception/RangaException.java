package ranga.exception;

/**
 * Represents exceptions specific to Ranga chatbot.
 * Used to signal errors in user input or command processing.
 */
public class RangaException extends Exception {

    /**
     * Creates a new RangaException with the given message.
     *
     * @param message The error message
     */
    public RangaException(String message) {
        super(message);
    }
}