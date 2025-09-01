package merlie.command;

import merlie.task.Task;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;
import merlie.listfile.ListFile;
import merlie.exception.MerlieException;

/**
 * Marks a task in the task list as done.
 */
public class MarkCommand extends Command {
    /** Index of the task to mark as done */
    private final int index;

    /**
     * Constructs a MarkCommand for the specified task index.
     *
     * @param index Index of task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Marks the task at the given index as done, updates list file, and shows output to the UI.
     */
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
