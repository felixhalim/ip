package duke;

import java.util.Scanner;

public class Ui {

    /**
     * Shows the welcome message during the program startup.
     */
    public void showWelcome() {
        printLogo();
        showLine();
        printGreetings();
        showLine();
    }

    /**
     * Shows the farewell message during the program termination.
     */
    public void showFarewells() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Shows the message of corresponding error.
     *
     * @param errorMessage the description of the error.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        System.out.print(">> ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private void printGreetings() {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
    }

    private void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
