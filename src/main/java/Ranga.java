import java.util.Scanner;

public class Ranga {

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

        // Storage for up to 100 items
        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println(" Welcome to Vought™ Interactive Systems");
        System.out.println(" I'm Ranga, your favourite VoughtBot! Definitely not a supe. Trust me.");
        System.out.println(logo);
        System.out.println(" We record everything you say for quality assurance purposes. Type 'list' to see stored items, or 'bye' to GTFO.");
        System.out.println("____________________________________________________________");

        label:
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();
            
            switch (command) {
            case "bye":
                System.out.println(" Thank you for your commitment to keeping supes safe. Try not to cause an international incident!");
                break label;
            case "":
                System.out.println(" Say something or I'll ping Homelander.");
                break;
            case "list":
                if (taskCount == 0) {
                    System.out.println(" Nothing stored. Even The Deep has more going on.");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks[i]);
                    }
                }
                break;
            default:
                if (taskCount < 100) {
                    tasks[taskCount] = command;
                    taskCount++;
                    System.out.println(" added: " + command);
                } else {
                    System.out.println(" Memory full. This is why we can't have nice things.");
                }
                break;
            }
        }

        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
