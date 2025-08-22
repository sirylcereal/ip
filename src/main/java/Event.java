public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean isDuplicate(Task other) {
        if (this.getDescription().equalsIgnoreCase(other.getDescription())) {
            if (other instanceof Event e) {
                return this.from.equalsIgnoreCase(e.getFrom())
                        && this.to.equalsIgnoreCase(e.getTo());
            }
            return true;
        }
        return false;
    }


    @Override
    public boolean updateIfSameDesc(Task other) {
        if (other instanceof Event e) {
            if (this.getDescription().equalsIgnoreCase(e.getDescription())) {
                this.from = e.getFrom();
                this.to = e.getTo();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}