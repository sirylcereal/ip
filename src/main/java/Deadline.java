/**
 * Represents a Deadline task with a due date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline with description and due date.
     *
     * @param description Task description.
     * @param by Due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of this task.
     *
     * @return Deadline as a string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets or updates the deadline of this task.
     *
     * @param by The new deadline.
     */
    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public boolean isDuplicate(Task other) {
        if (this.isSameDescription(other)) {
            if (other instanceof Deadline d) {
                return this.by.equalsIgnoreCase(d.getBy());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isUpdateSuccessful(Task other){
        if (this.isSameDescription(other) && other instanceof Deadline d) {
            this.by = d.getBy();
            return true;
        }
        return false;
    }

    @Override
    public String format() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}