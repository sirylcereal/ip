import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task with a start and end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private boolean hasToTime;
    private boolean hasFromTime;
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
     * Constructs an Event with the given description, start time and end time.
     *
     * @param description Description of the event.
     * @param from        Start time of the event.
     * @param to          End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        parseFromDate(from);
        parseToDate(to);
    }

    private void parseFromDate(String date) {
        for (DateTimeFormatter format : TIME_FORMATS) {
            try {
                this.from = LocalDateTime.parse(date, format);
                this.hasFromTime = true;
                return;
            } catch (DateTimeParseException e) {
            }
        }
        for (DateTimeFormatter format : DATE_ONLY_FORMATS) {
            try {
                LocalDate localDate = LocalDate.parse(date, format);
                this.from = localDate.atStartOfDay();
                this.hasFromTime = false;
                return;
            } catch (DateTimeParseException e) {
            }
        }
        throw new IllegalArgumentException();
    }

    private void parseToDate(String date) {
        for (DateTimeFormatter format : TIME_FORMATS) {
            try {
                this.to = LocalDateTime.parse(date, format);
                this.hasToTime = true;
                return;
            } catch (DateTimeParseException e) {
            }
        }
        for (DateTimeFormatter format : DATE_ONLY_FORMATS) {
            try {
                LocalDate localDate = LocalDate.parse(date, format);
                this.to = localDate.atStartOfDay();
                this.hasToTime = false;
                return;
            } catch (DateTimeParseException e) {
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Returns the start time of the event.
     *
     * @return Start time as a LocalDateTime.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return End time as a LocalDateTime.
     */
    public LocalDateTime getTo() {
        return to;
    }

    private boolean getHasFromTime() {
        return this.hasFromTime;
    }

    private boolean getHasToTime() {
        return this.hasToTime;
    }

    /**
     * Sets or updates the start time of the event.
     *
     * @param from The new start time.
     */
    public void setFrom(String from) {
        parseFromDate(from);
    }

    /**
     * Sets or updates the end time of the event.
     *
     * @param to The new end time.
     */
    public void setTo(String to) {
        parseToDate(to);
    }

    @Override
    public boolean isDuplicate(Task other) {
        if (this.isSameDescription(other)) {
            if (other instanceof Event e) {
                return this.from.equals(e.getFrom())
                        && this.to.equals(e.getTo());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isUpdateSuccessful(Task other) {
        if (this.isSameDescription(other) && other instanceof Event e) {
            this.from = e.getFrom();
            this.to = e.getTo();
            this.hasFromTime = e.getHasFromTime();
            this.hasToTime = e.getHasToTime();
            return true;
        }
        return false;
    }

    @Override
    public String format() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " /to " + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        String fromStr;
        String toStr;

        if (hasFromTime) {
            fromStr = from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
        } else {
            fromStr = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        if (hasToTime) {
            toStr = to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
        } else {
            toStr = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        return "[E]" + super.toString() + " (from: " + fromStr + "; to: " + toStr + ")";
    }
}