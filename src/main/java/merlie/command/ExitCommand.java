package merlie.command;

import merlie.tasklist.TaskList;
import merlie.ui.Ui;
import merlie.listfile.ListFile;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, ListFile listFile) {
        ui.end();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
