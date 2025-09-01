package merlie.command;

import merlie.task.Task;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;
import merlie.listfile.ListFile;
import merlie.exception.MerlieException;

/**
 * Marks a task in the task list as not done.
 */
public class UnmarkCommand extends Command {
    /** Index of the task to mark as undone */
    private final int index;

    /**
     * Constructs an UnmarkCommand for the specified task index.
     *
     * @param index Index of task to mark undone.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Marks the task at the given index as not done, updates list file, and shows output to the UI.
     */
    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) throws MerlieException {
        if (index < 0 || index >= list.size()) {
            ui.errorOutput("enter a valid task number");
            return;
        }

        Task task = list.getTask(index);
        list.markUndone(index);
        listFile.save(list);
        ui.markUndoneOutput(list.getTask(index));
    }
}
