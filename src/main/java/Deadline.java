import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a task with a deadline
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDateTime dateTime;

    /**
     * Constructor to create a new Deadline object
     *
     * @param description string that includes the name or description of a task
     * @param dateTime LocalDateTime object that includes the date/time of the deadline
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Gets the date of the deadline
     *
     * @return date as a string
     */
    public String date() {
        this.by = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return by;
    }

    /**
     * Takes the properties of the deadline and formats it in a string
     *
     * @return the properties as a string
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + date() + ")";
    }

    /**
     *
     * @return D for deadline
     */
    @Override
    public String getType() {
        return "D";
    }
}

