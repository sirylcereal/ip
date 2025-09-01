package merlie.command;

import merlie.task.Task;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;
import merlie.listfile.ListFile;
import merlie.exception.MerlieException;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand for the specified task index.
     *
     * @param index Index of task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Deletes the task at the specified index from the list, updates list file, and shows output to the UI.
     */
    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) throws MerlieException {
        if (index < 0 || index >= list.size()) {
            ui.errorOutput("enter a valid task number");
            return;
        }

        Task task = list.getTask(index);
        list.delete(index);
        listFile.save(list);
        ui.deleteOutput(task, list.size());
    }
}
