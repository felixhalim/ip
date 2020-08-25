import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static void printFarewells() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printGreetings() {
        printHorizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner in = new Scanner(System.in);
        String userResponse;
        List<String> list = new ArrayList<>();

        printGreetings();
        do {
            userResponse = in.nextLine();
            if (userResponse.equals("bye")) continue;
            printHorizontalLine();
            if (userResponse.equals("list")) {

                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            } else {
                list.add(userResponse);
                System.out.println("added: " + userResponse);
            }
            printHorizontalLine();
        } while (!userResponse.equals("bye"));
        printFarewells();
    }
}
