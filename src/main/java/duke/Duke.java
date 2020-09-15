package duke;

import duke.exception.EmptyArgumentException;
import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void main(String[] args) throws IOException {
        printLogo();
        printGreetings();
        startChatbot();
        printFarewells();
    }

    private static void startChatbot() throws IOException {
        String filePath = "C:\\Users\\felix\\OneDrive - National University of Singapore\\Semester " +
                "3\\CS2113t\\ip\\data\\duke.txt";

        Scanner in = new Scanner(System.in);
        String command;
        List<Task> list = new ArrayList<>();
        loadData(list, filePath);
        while (in.hasNextLine()) {
            saveData(list, filePath);
            command = in.nextLine();
            if (command.equals("bye")) break;
            printHorizontalLine();
            try {
                if (command.equals("list")) {
                    int size = list.size();
                    if (size == 0) {
                        System.out.println("You have nothing on your list! Why not add one :D");
                    } else {
                        String listPlurality = size > 1 ? "are" : "is";
                        String taskPlurality = size > 1 ? "tasks" : "task";
                        System.out.printf("Here %s the %s in your list:%n", listPlurality, taskPlurality);
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + "." + list.get(i).getTask());
                        }
                    }
                } else if (command.startsWith("done")) {
                    int taskIdentifier;
                    try {
                        taskIdentifier = Integer.parseInt(command.split(" ")[1]);
                        if (taskIdentifier > 0 && taskIdentifier <= list.size()) {
                            list.get(taskIdentifier - 1).markAsDone();
                            System.out.println("Okay! I've marked this task as done: Keep on going!");
                            System.out.println(list.get(taskIdentifier - 1).getTask());
                        } else {
                            System.out.println("Sorry, I can't find the task :(");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(">.< The task identifier supposed to be number! Here is the correct format" +
                                " \"done <number>\"");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(">.< You can't leave the task identifier empty! The format supposed to " +
                                "be \"done <number>\"");
                    }
                } else if (command.startsWith("delete")) {
                    int taskIdentifier;
                    try {
                        taskIdentifier = Integer.parseInt(command.split(" ")[1]);
                        if (taskIdentifier > 0 && taskIdentifier <= list.size()) {
                            System.out.println("Noted. I've removed this task");
                            System.out.println(" " + list.get(taskIdentifier - 1).getTask());
                            list.remove(taskIdentifier - 1);
                            int size = list.size();
                            System.out.printf("Now you have %d %s in the list.%n", size, size > 1 ? "tasks" : "task");
                        } else {
                            System.out.println("Sorry, I can't find the task :(");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(">.< The task identifier supposed to be number! Here is the correct format" +
                                " \"delete <number>\"");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(">.< You can't leave the task identifier empty! The format supposed to " +
                                "be \"delete <number>\"");
                    }
                } else {
                    if (command.startsWith("todo")) {
                        // e.g. : todo finish ip
                        try {
                            String description = command.split(" ", 2)[1];
                            list.add(new ToDo(description));
                        } catch (IndexOutOfBoundsException e) {
                            throw new EmptyArgumentException();
                        }
                    } else if (command.startsWith("deadline")) {
                        // e.g. : deadline finish ip /by tomorrow
                        try {
                            String parsedResponse = command.split(" ", 2)[1];
                            String description = parsedResponse.split(" /by ")[0];
                            String date = parsedResponse.split(" /by ")[1];
                            list.add(new Deadline(description, date));
                        } catch (IndexOutOfBoundsException e) {
                            throw new EmptyArgumentException();
                        }
                    } else if (command.startsWith("event")) {
                        // e.g. : event tp meeting /at monday, 7pm-8pm
                        try {
                            String parsedResponse = command.split(" ", 2)[1];
                            String description = parsedResponse.split(" /at ")[0];
                            String date = parsedResponse.split(" /at ")[1];
                            list.add(new Event(description, date));
                        } catch (IndexOutOfBoundsException e) {
                            throw new EmptyArgumentException();
                        }
                    } else {
                        throw new IllegalCommandException();
                    }
                    int size = list.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(size - 1).getTask());
                    System.out.printf("Now you have %d %s in the list.%n", size, size > 1 ? "tasks" : "task");
                }
            } catch (IllegalCommandException e) {
                System.out.println("Oops, I don't understand your command :(");
            } catch (EmptyArgumentException e) {
                System.out.println(">.< Ouch! Please check your argument");
            }
            printHorizontalLine();
        }
    }

    private static void saveData(List<Task> list, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : list) {
            String taskInformation = t.getTask();
            String taskType = taskInformation.substring(1, 2);
            String taskStatus = (taskInformation.substring(4, 5).equals("V") ? "1" : "0");
            String taskDescription = taskInformation.substring(7);
            if (taskDescription.contains("(by:")) {
                String taskTime = taskDescription.substring(taskDescription.indexOf("(by: ") + 5,
                        taskDescription.indexOf(")", taskDescription.indexOf("(by: ")));
                taskDescription = taskDescription.substring(0, taskDescription.indexOf("(by:") - 1);
                fw.write(taskType + ";" + taskStatus + ";" + taskDescription + ";" + taskTime);
            } else if (taskDescription.contains("(at:")) {
                String taskTime = taskDescription.substring(taskDescription.indexOf("(at: ") + 5,
                        taskDescription.indexOf(")", taskDescription.indexOf("(at: ")));
                taskDescription = taskDescription.substring(0, taskDescription.indexOf("(at:") - 1);
                fw.write(taskType + ";" + taskStatus + ";" + taskDescription + ";" + taskTime);
            } else {
                fw.write(taskType + ";" + taskStatus + ";" + taskDescription);
            }
            fw.write("\n");
        }
        fw.close();
    }

    private static void loadData(List<Task> list, String filePath) {
        try {
            File data = new File(filePath);
            Scanner dataEntry = new Scanner(data);
            while (dataEntry.hasNext()) {
                String[] parsedData = dataEntry.nextLine().split(";", 4);
                String type = parsedData[0];
                String status = parsedData[1];
                String description = parsedData[2];
                if (type.equalsIgnoreCase("t")) {
                    list.add(new ToDo(description, status));
                } else if (type.equalsIgnoreCase("d")) {
                    String date = parsedData[3];
                    list.add(new Deadline(description, date, status));
                } else if (type.equalsIgnoreCase("e")) {
                    String date = parsedData[3];
                    list.add(new Event(description, date, status));
                } else {
                    System.out.println("Format Error!");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.out.println("File not found");
        }
    }
}
