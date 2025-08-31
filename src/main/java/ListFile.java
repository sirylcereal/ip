import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Handles loading and saving of tasks to a file.
 */
public class ListFile {
    private final String filePath;

    /**
     * Constructs a ListFile handler with the specified file path.
     *
     * @param filePath Path to the stored list file.
     */
    public ListFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from file.
     * Creates file and directories if they do not exist.
     *
     * @return List of tasks.
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
                    if (task != null) {
                        list.add(task);
                    }
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
     * Saves tasks to file.
     *
     * @param tasks List of tasks to save.
     */
    public void save(TaskList list) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : list) {
                fw.write(task.format() + "\n");
            }
        } catch (IOException e) {
            throw new MerlieException("error with saving tasks: " + e.getMessage());
        }
    }
}
