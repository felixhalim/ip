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
        List<Task> list = new ArrayList<>();

        printGreetings();
        do {
            userResponse = in.nextLine();
            if (userResponse.equals("bye")) continue;
            printHorizontalLine();
            if (userResponse.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ".[" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription());
                }
            } else if (userResponse.startsWith("done")) {
                int taskIdentifier = Integer.parseInt(userResponse.split(" ")[1]);
                if (taskIdentifier > 0 && taskIdentifier <= list.size()) {
                    list.get(taskIdentifier - 1).markAsDone();
                    System.out.println("Okay! \"" + list.get(taskIdentifier - 1).getDescription() + "\" is marked as "
                            + "done" + ". " + "Keep on going!");
                } else {
                    System.out.println("Sorry, I can't find the task :(");
                }
            } else {
                list.add(new Task(userResponse));
                System.out.println("New task added: " + userResponse);
            }
            printHorizontalLine();
        } while (!userResponse.equals("bye"));
        printFarewells();
    }
}
