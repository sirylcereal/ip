import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            // Ensure file exists
            if (!Files.exists(path)) {
                Files.createFile(path);
                return tasks; // empty list
            }

            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Task task = Task.process(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Merlie: Rawrr, error with loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves tasks to file.
     *
     * @param tasks List of tasks to save.
     */
    public void save(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.format() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Merlie: Rawrr, error with saving tasks: " + e.getMessage());
        }
    }
}
