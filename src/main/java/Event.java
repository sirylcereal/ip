/**
 * Represents an Event task with a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event with the given description, start time and end time.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return Start time as a string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return End time as a string.
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets or updates the start time of the event.
     *
     * @param from The new start time.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Sets or updates the end time of the event.
     *
     * @param to The new end time.
     */
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean isDuplicate(Task other) {
        if (this.isSameDescription(other)) {
            if (other instanceof Event e) {
                return this.from.equalsIgnoreCase(e.getFrom())
                        && this.to.equalsIgnoreCase(e.getTo());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isUpdateSuccessful(Task other){
        if (this.isSameDescription(other) && other instanceof Event e) {
            this.from = e.getFrom();
            this.to = e.getTo();
            return true;
        }
        return false;
    }

    @Override
    public String format() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " /to " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}