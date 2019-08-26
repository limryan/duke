import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");

        chatbot();
    }

    private static void chatbot() {
        boolean exitCondition = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        int items = 0;

        while (!exitCondition) {
            String string = scanner.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(string);
            String command = tokenizer.nextToken();
            String description = null;
            String date = null;
            if (tokenizer.hasMoreTokens()) description = tokenizer.nextToken("/\n");
            if (tokenizer.hasMoreTokens()) date = tokenizer.nextToken("\n");

            System.out.println("________________________________________");
            switch (command) {
                case "bye":
                    exitCondition = true;
                    System.out.println("    Bye. Hope to see you again soon!");
                    break;
                case "list":
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < items; i++) {
                        System.out.println("    " + (i + 1) + ". " + list.get(i).toString());
                    }
                    break;
                case "todo":
                    try {
                        list.add(new Todo(description.substring(1)));
                        System.out.println("    Got it. I've added this task:\n" + "      "
                                + list.get(items).toString());
                        items++;
                        System.out.println("    Now you have " + items + " task(s) in the list.");
                    } catch (NullPointerException e) {
                        System.out.println("    OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;
                case "deadline":
                    try {
                        list.add(new Deadline(description.substring(1), date.substring(4)));
                        System.out.println("    Got it. I've added this task:\n" + "      "
                                + list.get(items).toString());
                        items++;
                        System.out.println("    Now you have " + items + " task(s) in the list.");
                    } catch (NullPointerException e) {
                        System.out.println("    OOPS!!! The description/date of a deadline cannot be empty.");
                    }
                    break;
                case "event":
                    try {
                        list.add(new Event(description.substring(1), date.substring(4)));
                        System.out.println("    Got it. I've added this task:\n" + "      "
                                + list.get(items).toString());
                        items++;
                        System.out.println("    Now you have " + items + " task(s) in the list.");
                    } catch (NullPointerException e) {
                        System.out.println("    OOPS!!! The description/date of an event cannot be empty.");
                    }
                    break;
                case "done":
                    try {
                        int item = Integer.parseInt(description.substring(1));
                        list.get(item - 1).markAsDone();
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("      [\u2713] " + list.get(item - 1).description);
                    } catch (NullPointerException e) {
                        System.out.println("    OOPS!!! Please enter an item number.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("    OOPS!!! Item not found.");
                    }
                    break;
                default:
                    System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("________________________________________");
        }
    }
}