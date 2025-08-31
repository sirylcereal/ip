public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) {
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
