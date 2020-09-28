package duke;

import duke.exception.DukeException;
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

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(List<Task> list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : list) {
                String taskInformation = t.getTask();
                String taskType = taskInformation.substring(1, 2);
                String taskStatus = (taskInformation.charAt(4) == 'V' ? "1" : "0");
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
        } catch (IOException e) {
            throw new DukeException("IO Error [" + e + "]");
        }
    }

    public List<Task> load() throws DukeException {
        List<Task> list = new ArrayList<>();
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
            throw new DukeException("File Not Found [" + e + "]");
        }
        return list;
    }
}