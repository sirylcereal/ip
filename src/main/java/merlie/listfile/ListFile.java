package merlie.listfile;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import merlie.exception.MerlieException;
import merlie.task.Task;
import merlie.tasklist.TaskList;

/**
 * Handles saving and loading of tasks to and from a file.
 */
public class ListFile {
    private final String filePath;

    /**
     * Constructs a ListFile with the specified file path.
     *
     * @param filePath Path to the stored list file.
     */
    public ListFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from file. Creates file and directories if they do not exist.
     *
     * @return TaskList containing the tasks read from the file.
     */
    public TaskList load() {
        TaskList list = new TaskList();
        Path path = Paths.get(filePath);
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            // Ensure file exists
            if (!Files.exists(path)) {
                Files.createFile(path);
                return list; // empty list
            }

            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                try {
                    Task task = Task.process(line);
                    list.add(task);
                } catch (MerlieException e) {
                    System.out.println("[Warning] Cannot load line: " + line + "\n" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("[Warning] Cannot load tasks: " + e.getMessage());
        }
        return list;
    }

    /**
     * Saves the tasks to file.
     *
     * @param list TaskList to save to file.
     * @throws MerlieException If an error occurs during saving.
     */
    public void save(TaskList list) throws MerlieException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : list) {
                fw.write(task.format() + "\n");
            }
        } catch (IOException e) {
            throw new MerlieException("error with saving tasks: " + e.getMessage());
        }
    }
}
