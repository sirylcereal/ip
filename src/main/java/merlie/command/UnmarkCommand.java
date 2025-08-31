public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) {
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
