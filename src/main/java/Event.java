public class Event extends Task{
    protected String at;
    protected int day;
    protected int month;
    protected int year;
    protected int time;

    public Event(String description, int day, int month, int year, int time) {
        super(description);
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
    }

    public String date() {
        at = day + "/" + month + "/" + year + " " + time;
        return at;
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + date() + ")";
    }
}
