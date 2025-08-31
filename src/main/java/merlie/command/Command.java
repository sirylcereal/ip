public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, ListFile listFile);
    public boolean isExit() {
        return false;
    }
}