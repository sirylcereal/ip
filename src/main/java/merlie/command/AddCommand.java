package merlie.command;

import merlie.task.Task;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;
import merlie.listfile.ListFile;
import merlie.exception.MerlieException;

public abstract class AddCommand extends Command {
    protected final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) throws MerlieException {
        if (!list.isTaskInList(task, ui, listFile)) {
            list.add(task);
            listFile.save(list);
            ui.addOutput(task, list.size());
        }
    }
}
