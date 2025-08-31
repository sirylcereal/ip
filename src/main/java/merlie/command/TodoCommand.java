package merlie.command;

import merlie.task.Todo;

public class TodoCommand extends AddCommand {
    public TodoCommand(String description) {
        super(new Todo(description));
    }
}
