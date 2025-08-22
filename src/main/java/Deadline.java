public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public boolean isDuplicate(Task other) {
        if (this.getDescription().equalsIgnoreCase(other.getDescription())) {
            if (other instanceof Deadline d) {
                return this.by.equalsIgnoreCase(d.getBy());
            }
            return true;
        }
        return false;
    }


    @Override
    public boolean updateIfSameDesc(Task other) {
        if (other instanceof Deadline d) {
            if (this.getDescription().equalsIgnoreCase(d.getDescription())) {
                this.by = d.getBy();
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}