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
                int size = list.size();
                if (size == 0) {
                    System.out.println("You have nothing on your list! Why not add one :D");
                    continue;
                }
                System.out.printf("Here %s the %s in your list:%n", size > 1 ? "are" : "is", size > 1 ? "tasks" :
                        "task");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i).getTask());
                }
            } else if (userResponse.startsWith("done")) {
                int taskIdentifier = Integer.parseInt(userResponse.split(" ")[1]);
                if (taskIdentifier > 0 && taskIdentifier <= list.size()) {
                    list.get(taskIdentifier - 1).markAsDone();
                    System.out.println("Okay! I've marked this task as done: Keep on going!");
                    System.out.println(list.get(taskIdentifier - 1).getTask());
                } else {
                    System.out.println("Sorry, I can't find the task :(");
                }
            } else {
                if (userResponse.startsWith("todo")) {
                    String description = userResponse.split(" ", 2)[1];
                    list.add(new ToDo(description));
                } else if (userResponse.startsWith("deadline")) {
                    String parsedResponse = userResponse.split(" ", 2)[1];
                    String description = parsedResponse.split(" /by ")[0];
                    String date = parsedResponse.split(" /by ")[1];
                    list.add(new Deadline(description, date));
                } else if (userResponse.startsWith("event")) {
                    String parsedResponse = userResponse.split(" ", 2)[1];
                    String description = parsedResponse.split(" /at ")[0];
                    String date = parsedResponse.split(" /at ")[1];
                    list.add(new Event(description, date));
                } else {
                    System.out.println("Unknown error!");
                    continue;
                }
                int size = list.size();
                System.out.println("Got it. I've added this task:");
                System.out.println(list.get(size - 1).getTask());
                System.out.printf("Now you have %d %s in the list.%n", size, size > 1 ? "tasks" : "task");
            }
            printHorizontalLine();
        } while (!userResponse.equals("bye"));
        printFarewells();
    }
}
