import java.util.ArrayList;

public class Ui {

    public void showLogo() {
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

    public void showList(ArrayList<Task> list) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < Task.totalItems; i++) {
            System.out.println("    " + (i + 1) + ". " + list.get(i).toString());
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

    public void showFind(ArrayList<Task> list, String query) {
        System.out.println("    Here are the matching tasks in your list:");
        int j = 1;
        for (Task task : list) {
            if (task.description.contains(query.trim())) {
                System.out.println(j + ". " + task.toString());
                j++;
            }
        }
    }
}
