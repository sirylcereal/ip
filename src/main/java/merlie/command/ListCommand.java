package merlie.command;

import merlie.tasklist.TaskList;
import merlie.ui.Ui;
import merlie.listfile.ListFile;

/**
 * Displays all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     * Displays all tasks in the list to the UI.
     */
    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) {
        if (list.isEmpty()) {
            ui.taskSizeOutput(0);
        } else {
            ui.showList(list);
        }
    }
}
