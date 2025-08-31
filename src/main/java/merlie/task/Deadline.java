package merlie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a due date.
 */
public class Deadline extends Task {
    private LocalDateTime by; // store the deadline as LocalDate
    private boolean hasTime;

    /**
     * Constructs a Deadline with description, due date, and time flag.
     *
     * @param description Task description.
     * @param by Due date of the task.
     * @param hasTime Whether the due date includes a specific time.
     */
    public Deadline(String description, LocalDateTime by, boolean hasTime) {
        super(description);
        this.by = by;
        this.hasTime = hasTime;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    public boolean getHasTime() {
        return this.hasTime;
    }

    private void setBy(LocalDateTime by, boolean hasTime) {
        this.by = by;
        this.hasTime = hasTime;;
    }

    @Override
    public boolean isDuplicate(Task other) {
        if (this.isSameDescription(other)) {
            if (other instanceof Deadline d) {
                return this.by.equals(d.getBy());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isUpdateSuccessful(Task other){
        if (this.isSameDescription(other) && other instanceof Deadline d) {
            setBy(d.getBy(),d.getHasTime());
            return true;
        }
        return false;
    }

    @Override
    public String format() {
        if (hasTime) {
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                    by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else {
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                    by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    @Override
    public String toString() {
        if (hasTime) {
            return "[D]" + super.toString() + " (by: " +
                    by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " +
                    by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}
