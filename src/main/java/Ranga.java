import java.util.Scanner;

/**
 * Ranga is a task management chatbot with a Vought™ theme.
 * Supports three types of tasks: Todo, Deadline, and Event.
 * Users can add, list, mark, and unmark tasks through text commands.
 */
public class Ranga {

    private static final int MAX_TASKS = 100;
    private static final int MARK_COMMAND_OFFSET = 5;
    private static final int UNMARK_COMMAND_OFFSET = 7;
    private static final int TODO_COMMAND_OFFSET = 5;
    private static final int DEADLINE_COMMAND_OFFSET = 9;
    private static final int EVENT_COMMAND_OFFSET = 6;
    private static final int BY_TAG_LENGTH = 3;
    private static final int FROM_TAG_LENGTH = 5;
    private static final int TO_TAG_LENGTH = 3;
    private static final String SEPARATOR = "____________________________________________________________";

    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        run();
    }

    /**
     * Main program execution loop.
     * Handles user interaction until exit.
     */
    private static void run() {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        boolean running = true;
        while (running) {
            System.out.print("> ");
            String userInput = scanner.nextLine().trim();

            running = processCommand(userInput);
        }

        System.out.println(SEPARATOR);
        scanner.close();
    }

    /**
     * Prints the welcome greeting and ASCII logo.
     */
    private static void printGreeting() {
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
        System.out.println(" We record everything you say for quality assurance purposes. Type 'list' to see the stored Herogasm Files, or 'bye' to GTFO.");
    }

    /**
     * Processes user commands and delegates to appropriate handler methods.
     *
     * @param userInput The command string entered by the user
     * @return false if user wants to exit (bye command), true otherwise
     */
    private static boolean processCommand(String userInput) {
        switch (userInput) {
        case "bye":
            handleByeCommand();
            return false;

        case "":
            handleEmptyCommand();
            break;

        case "list":
            handleListCommand();
            break;

        default:
            handleDefaultCommand(userInput);
            break;
        }
        return true;
    }

    /**
     * Handles the "bye" command by printing a farewell message.
     */
    private static void handleByeCommand() {
        System.out.println(
                " Thank you for your commitment to keeping supes safe. Try not to cause an international incident!");
    }

    /**
     * Handles empty input by prompting the user to enter something.
     */
    private static void handleEmptyCommand() {
        System.out.println(" Say something or I'll ping Homelander.");
    }

    /**
     * Handles the "list" command by displaying all stored tasks.
     */
    private static void handleListCommand() {
        System.out.println(SEPARATOR);
        if (taskCount == 0) {
            System.out.println(" Nothing stored. Even The Deep has more going on.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + "." + tasks[i]);
            }
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Handles commands that don't match predefined cases.
     * Processes mark, unmark, todo, deadline, event commands.
     *
     * @param userInput The command string to process
     */
    private static void handleDefaultCommand(String userInput) {
        if (userInput.startsWith("mark ")) {
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
            System.out.println(" I don't know what that means :-(");
        }
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param userInput The full mark command string
     */
    private static void handleMarkCommand(String userInput) {
        int index = parseIndex(userInput, MARK_COMMAND_OFFSET, taskCount);
        if (index != -1) {
            tasks[index].markAsDone();
            System.out.println(" Nice! Homelander would be proud.");
            System.out.println("   " + tasks[index]);
        }
    }

    /**
     * Handles the "unmark" command to mark a task as not done.
     *
     * @param userInput The full unmark command string
     */
    private static void handleUnmarkCommand(String userInput) {
        int index = parseIndex(userInput, UNMARK_COMMAND_OFFSET, taskCount);
        if (index != -1) {
            tasks[index].markAsNotDone();
            System.out.println(" One more mistake and you'll be sent to Ashley for performance review.");
            System.out.println("   " + tasks[index]);
        }
    }

    /**
     * Adds a task to the task list and displays confirmation message.
     *
     * @param newTask The task to add
     */
    private static void addTask(Task newTask) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = newTask;
            taskCount++;
            System.out.println(SEPARATOR);
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + newTask);
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
            System.out.println(SEPARATOR);
        } else {
            System.out.println(" Memory full. This is why we can't have nice things.");
        }
    }

    /**
     * Handles the "todo" command to add a new todo task.
     * Expected format: todo DESCRIPTION
     *
     * @param userInput The full todo command string
     */
    private static void handleTodoCommand(String userInput) {
        String description = userInput.substring(TODO_COMMAND_OFFSET).trim();

        if (description.isEmpty()) {
            System.out.println(" The description of a todo cannot be empty.");
            return;
        }

        addTask(new Todo(description));
    }

    /**
     * Handles the "deadline" command to add a new deadline task.
     * Expected format: deadline DESCRIPTION /by DEADLINE
     *
     * @param userInput The full deadline command string
     */
    private static void handleDeadlineCommand(String userInput) {
        String details = userInput.substring(DEADLINE_COMMAND_OFFSET).trim();
        int byIndex = details.indexOf("/by");

        if (byIndex == -1) {
            System.out.println(" Please specify the deadline using /by.");
            return;
        }

        String description = details.substring(0, byIndex).trim();
        String by = details.substring(byIndex + BY_TAG_LENGTH).trim();

        if (description.isEmpty() || by.isEmpty()) {
            System.out.println(" The description and deadline cannot be empty.");
            return;
        }

        addTask(new Deadline(description, by));
    }

    /**
     * Handles the "event" command to add a new event task.
     * Expected format: event DESCRIPTION /from START /to END
     *
     * @param userInput The full event command string
     */
    private static void handleEventCommand(String userInput) {
        String details = userInput.substring(EVENT_COMMAND_OFFSET).trim();
        int fromIndex = details.indexOf("/from");
        int toIndex = details.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            System.out.println(" Please specify the event time using /from and /to.");
            return;
        }

        String description = details.substring(0, fromIndex).trim();
        String from = details.substring(fromIndex + FROM_TAG_LENGTH, toIndex).trim();
        String to = details.substring(toIndex + TO_TAG_LENGTH).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            System.out.println(" The description, start time, and end time cannot be empty.");
            return;
        }

        addTask(new Event(description, from, to));
    }

    /**
     * Parses the task index from a command string.
     *
     * @param userInput    The full command string
     * @param offset       The starting position of the index in the command
     * @param maxTaskCount The total number of tasks (for validation)
     * @return The parsed index (0-based), or -1 if parsing failed or index is invalid
     */
    private static int parseIndex(String userInput, int offset, int maxTaskCount) {
        int index;

        try {
            index = Integer.parseInt(userInput.substring(offset).trim()) - 1;
        } catch (NumberFormatException e) {
            System.out.println(" Black Noir doesn't have time to teach numbers. Last chance.");
            return -1;
        }

        if (index < 0 || index >= maxTaskCount) {
            System.out.println(" Tek Knight couldn't find that task. Keep trolling and we'll wire $1M from your account to BLM.");
            return -1;
        }

        return index;
    }
}