import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task with a due date.
 */
public class Deadline extends Task {
    private LocalDateTime by; // store the deadline as LocalDate
    private boolean hasTime;
    private static final DateTimeFormatter[] DATE_ONLY_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy") // add two-digit support
    };
    private static final DateTimeFormatter[] TIME_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
    };

    /**
     * Constructs a Deadline with description and due date.
     *
     * @param description Task description.
     * @param by Due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        parseDate(by);
    }

    private void parseDate(String date) {
        for (DateTimeFormatter format : TIME_FORMATS) {
            try {
                this.by =  LocalDateTime.parse(date, format);
                this.hasTime = true;
                return;
            } catch (DateTimeParseException e) {
            }
        }
        for (DateTimeFormatter format : DATE_ONLY_FORMATS) {
            try {
                LocalDate localDate = LocalDate.parse(date, format);
                this.by = localDate.atStartOfDay();
                this.hasTime = false;
                return;
            } catch (DateTimeParseException e) {
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Returns the deadline of this task.
     *
     * @return Deadline as a LocalDate.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    private boolean getHasTime() {
        return this.hasTime;
    }

    /**
     * Sets or updates the deadline of this task.
     *
     * @param by The new deadline.
     */
    public void setBy(String by) {
        parseDate(by);
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
            this.by = d.getBy();
            this.hasTime = d.getHasTime();
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
