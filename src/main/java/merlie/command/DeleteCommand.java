public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) {
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
