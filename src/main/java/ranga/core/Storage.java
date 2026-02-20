package ranga.core;

import ranga.task.Task;
import ranga.task.Todo;
import ranga.task.Deadline;
import ranga.task.Event;
import ranga.exception.RangaException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to/from the hard disk.
 */
public class Storage {

    private static final String FILE_PATH = "./data/ranga.txt";

    /**
     * Loads tasks from the data file.
     * Returns an empty list if the file doesn't exist.
     * Skips corrupted lines with a warning.
     *
     * @return ArrayList of tasks loaded from disk
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                try {
                    tasks.add(parseTask(line));
                } catch (RangaException e) {
                    System.out.println(" [Warning] Skipping corrupted line " + lineNumber + ": " + line);
                }
            }
        } catch (IOException e) {
            System.out.println(" [Warning] Could not read data file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves all tasks to the data file.
     * Creates the data directory if it doesn't exist.
     *
     * @param tasks The list of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // creates ./data/ if it doesn't exist

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(serialise(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(" [Warning] Could not save tasks: " + e.getMessage());
        }
    }

    /**
     * Converts a Task to its file format string.
     */
    private String serialise(Task task) throws IllegalArgumentException {
        String done = task.getStatusIcon().equals("X") ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + done + " | " + task.getDescription();
        } else if (task instanceof Deadline d) {
            return "D | " + done + " | " + d.getDescription() + " | " + d.getBy();
        } else if (task instanceof Event e) {
            return "E | " + done + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
        }
        throw new IllegalArgumentException("Unknown task type");
    }

    /**
     * Parses a line from the data file into a Task.
     *
     * @throws RangaException if the line format is invalid
     */
    private Task parseTask(String line) throws RangaException {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new RangaException("Too few fields.");
        }

        String type = parts[0].trim();
        String doneFlag = parts[1].trim();
        String description = parts[2].trim();

        if (!doneFlag.equals("0") && !doneFlag.equals("1")) {
            throw new RangaException("Invalid done flag.");
        }

        boolean isDone = doneFlag.equals("1");
        Task task;

        switch (type) {
        case "T":
            if (parts.length != 3) {
                throw new RangaException("Todo should have exactly 3 fields.");
            }
            task = new Todo(description);
            break;
        case "D":
            if (parts.length != 4) {
                throw new RangaException("Deadline should have exactly 4 fields.");
            }
            task = new Deadline(description, parts[3].trim());
            break;
        case "E":
            if (parts.length != 5) {
                throw new RangaException("Event should have exactly 5 fields.");
            }
            task = new Event(description, parts[3].trim(), parts[4].trim());
            break;
        default:
            throw new RangaException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}