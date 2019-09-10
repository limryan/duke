/**
 * Class that represents a task that is a todo
 */
public class Todo extends Task {

    /**
     * Constructor that creates a todo task
     * @param description name of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a string that contains information about the todo task
     *
     * @return formatted string containing attributes about the task
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
