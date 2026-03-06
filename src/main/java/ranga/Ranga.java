package ranga;

import ranga.command.Command;
import ranga.core.Parser;
import ranga.core.Storage;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.ui.UI;

public class Ranga {

    private final UI ui;
    private final TaskList tasks;
    private final Storage storage;

    public Ranga() {
        ui = new UI();
        storage = new Storage();
        tasks = new TaskList(storage);
    }

    public static void main(String[] args) {
        new Ranga().run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (RangaException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}