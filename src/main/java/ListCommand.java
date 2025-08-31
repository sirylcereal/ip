public class ListCommand extends Command {
    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) {
        if (list.isEmpty()) {
            ui.taskSizeOutput(0);
        } else {
            ui.showList(list);
        }
    }
}
