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

        System.out.println("____________________________________________________________");
        System.out.println(" Welcome to Vought™ Interactive Systems");
        System.out.println(" I'm Ranga, your favourite VoughtBot! Definitely not a supe. Trust me.");
        System.out.println(logo);
        System.out.println(" Type a command or type 'bye' if you want out NOW.");
        System.out.println("____________________________________________________________");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            // Case-sensitive exit
            if (command.equals("bye")) {
                System.out.println(" Clocking out. Try not to cause an international incident.");
                break;
            }
            else if (command.isEmpty()) {
                System.out.println(" Say something or I'll ping Homelander.");
            }
            else {
                System.out.println(command);
            }
        }

        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
