package merlie.command;

import merlie.tasklist.TaskList;
import merlie.ui.Ui;
import merlie.listfile.ListFile;
import merlie.exception.MerlieException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, ListFile listFile) throws MerlieException;
    public boolean isExit() {
        return false;
    }
}