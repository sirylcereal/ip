package merlie.command;

import java.util.ArrayList;
import java.util.List;

import merlie.listfile.ListFile;
import merlie.task.Task;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;

/**
 * Finds tasks whose descriptions contain a specified keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword Keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by displaying all tasks that contain the keyword in
     * their description.
     *
     * @param list TaskList to search in.
     * @param ui Ui object to display output.
     * @param listFile ListFile (not used for find command, can be null).
     */
    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) {
        List<Task> matches = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().toLowerCase().contains(this.keyword.toLowerCase())) {
                matches.add(task);
            }
        }

        if (matches.isEmpty()) {
            ui.errorOutput("no tasks found matching keyword: " + keyword);
        } else {
            ui.showMatches(matches);
        }
    }
}
