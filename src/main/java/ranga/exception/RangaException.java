package ranga.exception;

/**
 * Represents exceptions specific to Ranga chatbot.
 * Used to signal errors in user input or command processing.
 */
public class RangaException extends Exception {

    public RangaException(String message) {
        super(message);
    }
}