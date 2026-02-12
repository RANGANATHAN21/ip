import java.util.Scanner;

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
                running = processCommand(userInput);
            } catch (RangaException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.showLine();
        scanner.close();
    }

    /**
     * Processes user commands and delegates to appropriate handler methods.
     *
     * @param userInput The command string entered by the user
     * @return false if user wants to exit (bye command), true otherwise
     * @throws RangaException if there is an error processing the command
     */
    private boolean processCommand(String userInput) throws RangaException {
        if (userInput.equals("bye")) {
            ui.showGoodbye();
            return false;
        } else if (userInput.isEmpty()) {
            ui.showEmptyInputMessage();
        } else if (userInput.equals("list")) {
            handleListCommand();
        } else if (userInput.startsWith("mark ")) {
            handleMarkCommand(userInput);
        } else if (userInput.startsWith("unmark ")) {
            handleUnmarkCommand(userInput);
        } else if (userInput.startsWith("todo ")) {
            handleTodoCommand(userInput);
        } else if (userInput.startsWith("deadline ")) {
            handleDeadlineCommand(userInput);
        } else if (userInput.startsWith("event ")) {
            handleEventCommand(userInput);
        } else {
            throw new RangaException("Oi! Get yourself together, son.");
        }
        return true;
    }

    /**
     * Handles the "list" command by displaying all stored tasks.
     */
    private void handleListCommand() {
        ui.showTaskList(tasks.getTasks(), tasks.getTaskCount());
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param userInput The full mark command string
     * @throws RangaException if task index is invalid
     */
    private void handleMarkCommand(String userInput) throws RangaException {
        int index = Parser.parseTaskIndex(userInput, Parser.getMarkCommandOffset(), tasks.getTaskCount());
        tasks.markTask(index);
        ui.showTaskMarked(tasks.getTask(index));
    }

    /**
     * Handles the "unmark" command to mark a task as not done.
     *
     * @param userInput The full unmark command string
     * @throws RangaException if task index is invalid
     */
    private void handleUnmarkCommand(String userInput) throws RangaException {
        int index = Parser.parseTaskIndex(userInput, Parser.getUnmarkCommandOffset(), tasks.getTaskCount());
        tasks.unmarkTask(index);
        ui.showTaskUnmarked(tasks.getTask(index));
    }

    /**
     * Handles the "todo" command to add a new todo task.
     *
     * @param userInput The full todo command string
     * @throws RangaException if description is empty
     */
    private void handleTodoCommand(String userInput) throws RangaException {
        Task task = Parser.parseTodo(userInput);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getTaskCount());
    }

    /**
     * Handles the "deadline" command to add a new deadline task.
     *
     * @param userInput The full deadline command string
     * @throws RangaException if format is invalid or fields are empty
     */
    private void handleDeadlineCommand(String userInput) throws RangaException {
        Task task = Parser.parseDeadline(userInput);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getTaskCount());
    }

    /**
     * Handles the "event" command to add a new event task.
     *
     * @param userInput The full event command string
     * @throws RangaException if format is invalid or fields are empty
     */
    private void handleEventCommand(String userInput) throws RangaException {
        Task task = Parser.parseEvent(userInput);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getTaskCount());
    }
}