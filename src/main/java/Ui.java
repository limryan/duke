import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that deals with all interactions with a user
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor that starts the scanning of System input
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Method to display welcome and logo of Duke
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Method that prints out a line
     */
    public void showLine() {
        System.out.println("________________________________________");
    }

    /**
     * Method that confirms that a task has been added and the new total number
     * of tasks
     *
     * @param task the string containing the task that was added
     * @param totalItems total number of tasks in taskList
     */
    public void showCreateTask(String task, int totalItems) {
        System.out.println("    Got it. I've added this task:\n" + "      "
                + task + "\n"
                + "    Now you have " + totalItems + " task(s) in the list.");
    }

    /**
     * Method that prints out the tasks in the list
     *
     * @param tasks the TaskList containing all the tasks
     */
    public void showList(TaskList tasks) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < Task.totalItems; i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Method that confirms that a task has been deleted.
     *
     * @param task string containing information about the task
     * @param totalItems current number of items in the list
     */
    public void showDelete(String task, int totalItems) {
        System.out.println("    Noted. I've removed this task:\n" + "    " + task);
        System.out.println("    Now you have " + totalItems + " item(s) in your list.");
    }

    /**
     * Method that confirms that a task has been marked done.
     *
     * @param task string containing information about the task
     */
    public void showDone(String task) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      [\u2713] " + task);
    }

    /**
     * Method that prints out the results from a search of the task list
     *
     * @param results an array that contains the results in string format
     */
    public void showFind(ArrayList<String> results) {
        try {
            System.out.println("    Here are the matching tasks in your list:");
            for (String string : results) {
                System.out.println("    " + string);
            }
        } catch (NullPointerException e) {
            System.out.println("    No results found. Try another keyword.");
        }
    }

    /**
     * Method that reads the input that a user keys in
      * @return string of raw input
     */
    public String read() {
        return scanner.nextLine();
    }
}
