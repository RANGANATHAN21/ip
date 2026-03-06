package ranga;

import ranga.command.Command;
import ranga.core.Parser;
import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.ui.UI;

/**
 * Main class for the Ranga chatbot application.
 * Initializes components and runs the main interaction loop.
 */
public class Ranga {

    private final UI ui;
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Creates a new Ranga instance, initializing UI, storage, and task list.
     */
    public Ranga() {
        ui = new UI();
        storage = new Storage();
        tasks = new TaskList(storage);
    }

    /**
     * Entry point for the Ranga application.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Ranga().run();
    }

    /**
     * Runs the main loop, reading and executing commands until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isFinished = false;
        while (!isFinished) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isFinished = c.isExit();
            } catch (RangaException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}