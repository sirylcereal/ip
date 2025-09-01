package merlie.command;

import merlie.task.Event;

import java.time.LocalDateTime;

public class EventCommand extends AddCommand {
    public EventCommand(String description, LocalDateTime from,
            boolean hasFromTime, LocalDateTime to, boolean hasToTime) {
        super(new Event(description, from, hasFromTime, to, hasToTime));
    }
}