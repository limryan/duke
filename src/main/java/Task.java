/**
 * Class that represents a task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalItems = 0;

    /**
     * Constructor to create a new Task object
     *
     * @param description string that includes the name or description of a task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalItems++;
    }

    /**
     * assigns the isDone variable as true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * @return date as a string
     */
    public String date() {
        return this.date();
    }

    /**
     * If the task is marked done, it will return tick. Else return cross
     *
     * @return tick or cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * @return T for task
     */
    public String getType() {
        return "T";
    }
}
