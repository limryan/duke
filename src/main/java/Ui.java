import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showLine() {
        System.out.println("________________________________________");
    }

    public void showCreateTask(String task, int totalItems) {
        System.out.println("    Got it. I've added this task:\n" + "      "
                + task + "\n"
                + "    Now you have " + totalItems + " task(s) in the list.");
    }

    public void showList(TaskList tasks) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < Task.totalItems; i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
    }

    public void showDelete(String task, int totalItems) {
        System.out.println("    Noted. I've removed this task:\n" + "    " + task);
        System.out.println("    Now you have " + totalItems + " item(s) in your list.");
    }

    public void showDone(String task) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      [\u2713] " + task);
    }

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

    public String read() {
        return scanner.nextLine();
    }
}
