package ranga.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ranga.exception.RangaException;
import ranga.task.Deadline;
import ranga.task.Event;
import ranga.task.Task;
import ranga.task.Todo;

/**
 * Handles loading and saving tasks to/from the hard disk.
 */
public class Storage {

    private static final String FILE_PATH = "./data/ranga.txt";

    /**
     * Loads tasks from the data file, skipping corrupted lines with a warning.
     * Returns an empty list if the file doesn't exist.
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
     * Saves all tasks to the data file, creating the data directory if it doesn't exist.
     *
     * @param tasks The list of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (!parentDir.mkdirs() && !parentDir.exists()) {
            System.out.println(" [Warning] Could not create data directory.");
            return;
        }
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(serialise(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(" [Warning] Could not save tasks: " + e.getMessage());
        }
    }

    /**
     * Converts a Task to its saved file format string.
     *
     * @param task The task to serialize
     * @return The formatted string representing the task
     * @throws IllegalArgumentException if the task type is unrecognized
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
     * @param line The raw line from the data file
     * @return The parsed Task
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
        Task task = createTask(type, description, parts);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates a Task from its type identifier, description, and raw data fields.
     *
     * @param type        The task type identifier ("T", "D", or "E")
     * @param description The task description
     * @param parts       The full array of split fields from the data file line
     * @return The constructed Task object
     * @throws RangaException if the type is unrecognized or field count is wrong
     */
    private Task createTask(String type, String description, String[] parts) throws RangaException {
        switch (type) {
        case "T" -> {
            if (parts.length != 3) {
                throw new RangaException("Todo should have exactly 3 fields...");
            }
            return new Todo(description);
        }
        case "D" -> {
            if (parts.length != 4) {
                throw new RangaException("Deadline should have exactly 4 fields...");
            }
            return new Deadline(description, parts[3].trim());
        }
        case "E" -> {
            if (parts.length != 5) {
                throw new RangaException("Event should have exactly 5 fields...");
            }
            return new Event(description, parts[3].trim(), parts[4].trim());
        }
        default -> throw new RangaException("Unknown task type: " + type);
        }
    }
}