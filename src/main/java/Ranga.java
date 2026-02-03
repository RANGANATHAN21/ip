import java.util.Scanner;

public class Ranga {

    private static final int MAX_TASKS = 100;
    private static final int MARK_COMMAND_OFFSET = 5;
    private static final int UNMARK_COMMAND_OFFSET = 7;

    public static void main(String[] args) {

        String logo =
                """
                         ____
                        |  _ \\  __ _ _ __   __ _  __ _
                        | |_) / _` | '_ \\ / _` |/ _` |
                        |  _ < (_| | | | | (_| | (_| |
                        |_| \\_\\__,_|_| |_|\\__, |\\__,_|
                                           |___/
                        """;

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        System.out.println(" Welcome to Vought™ Interactive Systems");
        System.out.println(" I'm Ranga, your favourite VoughtBot! Definitely not a supe. Trust me.");
        System.out.println(logo);
        System.out.println(" We record everything you say for quality assurance purposes. Type 'list' to see the stored Herogasm Files, or 'bye' to GTFO.");

        label:
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            switch (command) {
            case "bye":
                System.out.println(
                        " Thank you for your commitment to keeping supes safe. Try not to cause an international incident!");
                break label;

            case "":
                System.out.println(" Say something or I'll ping Homelander.");
                break;

            case "list":
                if (taskCount == 0) {
                    System.out.println(" Nothing stored. Even The Deep has more going on.");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        Task t = tasks[i];
                        System.out.println(
                                " " + (i + 1) + ".[" + t.getStatusIcon() + "] "
                                        + t.getDescription()
                        );
                    }
                }
                break;

            default:
                if (command.startsWith("mark ")) {
                    int index = parseIndex(command, MARK_COMMAND_OFFSET, taskCount);
                    if (index != -1) {
                        tasks[index].markAsDone();
                        System.out.println(" Nice! Homelander would be proud.");
                        System.out.println("   [X] " + tasks[index].getDescription());
                    }
                } else if (command.startsWith("unmark ")) {
                    int index = parseIndex(command, UNMARK_COMMAND_OFFSET, taskCount);
                    if (index != -1) {
                        tasks[index].markAsNotDone();
                        System.out.println(" One more mistake and you'll be sent to Ashley for performance review.");
                        System.out.println("   [ ] " + tasks[index].getDescription());
                    }
                } else {
                    if (taskCount < MAX_TASKS) {
                        tasks[taskCount] = new Task(command);
                        taskCount++;
                        System.out.println(" added: " + command);
                    } else {
                        System.out.println(" Memory full. This is why we can't have nice things.");
                    }
                }
                break;
            }
        }

        System.out.println("____________________________________________________________");
        scanner.close();
    }

    private static int parseIndex(String command, int start, int taskCount) {
        try {
            int index = Integer.parseInt(command.substring(start).trim()) - 1;

            if (index < 0 || index >= taskCount) {
                System.out.println(" Tek Knight couldn’t find that task. Keep trolling and we’ll wire $1M from your account to BLM.");
                return -1;
            }

            return index;
        } catch (NumberFormatException e) {
            System.out.println(" Black Noir doesn’t have time to teach numbers. Last chance.");
            return -1;
        }
    }
}
