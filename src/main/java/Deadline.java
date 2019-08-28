public class Deadline extends Task {

    protected String by;
    protected int day;
    protected int month;
    protected int year;
    protected int time;

    public Deadline(String description, int day, int month, int year, int time) {
        super(description);
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
    }

    public String date() {
        this.by = day + "/" + month + "/" + year + " " + time;
        return by;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + date() + ")";
    }
}

