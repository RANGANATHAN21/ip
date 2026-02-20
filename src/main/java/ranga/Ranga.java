package ranga;

import java.util.Scanner;

import ranga.core.CommandProcessor;
import ranga.core.TaskList;
import ranga.exception.RangaException;
import ranga.ui.UI;

/**
 * Ranga is a task management chatbot with a Vought™ theme.
 * Supports three types of tasks: Todo, Deadline, and Event.
 * Users can add, list, mark, and unmark tasks through text commands.
 */
public class Ranga {

    private final UI ui;
    private final TaskList tasks;

    /**
     * Creates a new Ranga chatbot instance.
     */
    public Ranga() {
        this.ui = new UI();
        this.tasks = new TaskList();
    }

    public static void main(String[] args) {
        new Ranga().run();
    }

    /**
     * Main program execution loop.
     * Handles user interaction until exit.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        ui.showWelcome();

        boolean running = true;
        while (running) {
            try {
                System.out.print("> ");
                String userInput = scanner.nextLine().trim();
                running = CommandProcessor.process(userInput, tasks, ui);
            } catch (RangaException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.showLine();
        scanner.close();
    }
}