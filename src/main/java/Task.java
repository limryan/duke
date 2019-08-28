public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalItems = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalItems++;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String date() {
        return this.date();
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
}
