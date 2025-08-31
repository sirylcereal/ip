package merlie.command;

import merlie.tasklist.TaskList;
import merlie.ui.Ui;
import merlie.listfile.ListFile;

/**
 * Terminates the chat when executed.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     * Executes exit behavior; no changes to tasks or storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, ListFile listFile) {
        ui.end();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
