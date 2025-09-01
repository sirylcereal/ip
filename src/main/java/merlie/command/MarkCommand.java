package merlie.command;

import merlie.task.Task;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;
import merlie.listfile.ListFile;
import merlie.exception.MerlieException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) throws MerlieException {
        if (index < 0 || index >= list.size()) {
            ui.errorOutput("enter a valid task number");
            return;
        }

        Task task = list.getTask(index);
        list.markDone(index);
        listFile.save(list);
        ui.markDoneOutput(list.getTask(index));
    }
}
