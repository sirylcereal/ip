import java.time.LocalDateTime;

public class DeadlineCommand extends AddCommand {
    public DeadlineCommand(String description, LocalDateTime by, boolean hasTime) {
        super(new Deadline(description, by, hasTime));
    }
}
