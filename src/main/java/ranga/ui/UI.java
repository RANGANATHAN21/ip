package ranga.ui;

import ranga.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interface interactions for the Ranga application.
 * Responsible for displaying messages, errors, and reading user input.
 */
public class UI {

    private static final String SEPARATOR = "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Prints the welcome greeting and ASCII logo.
     */
    public void showWelcome() {
        String logo =
                """
                         ____
                        |  _ \\  __ _ _ __   __ _  __ _
                        | |_) / _` | '_ \\ / _` |/ _` |
                        |  _ < (_| | | | | (_| | (_| |
                        |_| \\_\\__,_|_| |_|\\__, |\\__,_|
                                           |___/
                        """;

        System.out.println(" Welcome to Vought™ Interactive Systems");
        System.out.println(" I'm Ranga, your favourite VoughtBot! Definitely not a supe. Trust me.");
        System.out.println(logo);
        System.out.println(" We record everything you say for quality assurance purposes. Type 'list' to see the stored Herogasm Files, or 'bye' to go off-grid.");
    }

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    /**
     * Prints the goodbye message.
     */
    public void showGoodbye() {
        System.out.println(
                " Thank you for your commitment to keeping supes safe. Try not to cause an international incident!");
    }

    /**
     * Prints an error message with separators.
     *
     * @param errorMessage The error message to display
     */
    public void showError(String errorMessage) {
        System.out.println(SEPARATOR);
        System.out.println(" " + errorMessage);
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param task The task that was marked
     */
    public void showTaskMarked(Task task) {
        System.out.println(" Nice! Homelander would be proud.");
        System.out.println("   " + task);
    }

    /**
     * Prints a message when a task is unmarked.
     *
     * @param task The task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(" One more mistake and you'll be sent to Ashley for performance review.");
        System.out.println("   " + task);
    }

    /**
     * Prints a message when a task is added.
     *
     * @param task The task that was added
     * @param taskCount The total number of tasks after addition
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(SEPARATOR);
        System.out.println(" Right then. Added to the hit list:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list. Have fun!");
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a message when a task is deleted.
     *
     * @param task The task that was deleted
     * @param taskCount The total number of tasks after deletion
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(SEPARATOR);
        System.out.println(" Gotcha. I've eliminated this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the list of all tasks.
     *
     * @param tasks The ArrayList of tasks
     * @param taskCount The number of tasks in the list
     */
    public void showTaskList(ArrayList<Task> tasks, int taskCount) {
        System.out.println(SEPARATOR);
        if (taskCount == 0) {
            System.out.println(" Nothing stored. Even The Deep has more going on.");
        } else {
            System.out.println(" We ain't runnin' a charity. Get to it.");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(SEPARATOR);
    }

    public void showMatchingTasks(ArrayList<Task> tasks) {
        System.out.println(SEPARATOR);
        if (tasks.isEmpty()) {
            System.out.println(" No matching tasks found. Even Frenchie couldn't track those down.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(SEPARATOR);
    }
}