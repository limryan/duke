import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents an event
 */
public class Event extends Task {
    protected String at;
    protected LocalDateTime dateTime;

    /**
     * Constructor to create a new Event object
     *
     * @param description string that includes the name or description of a task
     * @param dateTime LocalDateTime object that includes the date/time of the event
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Gets the date of the event
     *
     * @return date as a string
     */
    public String date() {
        at = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return at;
    }

    /**
     * Takes the properties of the event and formats it in a string
     *
     * @return the properties as a string
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + date() + ")";
    }

    /**
     *
     * @return E for event
     */
    @Override
    public String getType() {
        return "E";
    }
}
