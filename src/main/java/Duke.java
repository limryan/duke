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
        Task[] list = new Task[100];
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
                        System.out.println("    " + (i + 1) + ". " + list[i].toString());
                    }
                    break;
                case "todo":
                    list[items] = new Todo(description.substring(1));
                    System.out.println("    Got it. I've added this task:\n" + "      "
                            + list[items].toString());
                    items++;
                    System.out.println("    Now you have " + items + " task(s) in the list.");
                    break;
                case "deadline":
                    list[items] = new Deadline(description.substring(1), date.substring(4));
                    System.out.println("    Got it. I've added this task:\n" + "      "
                            + list[items].toString());
                    items++;
                    System.out.println("    Now you have " + items + " task(s) in the list.");
                    break;
                case "event":
                    list[items] = new Event(description.substring(1), date.substring(4));
                    System.out.println("    Got it. I've added this task:\n" + "      "
                            + list[items].toString());
                    items++;
                    System.out.println("    Now you have " + items + " task(s) in the list.");
                    break;
                case "done":
                    int item = Integer.parseInt(description.substring(1));
                    list[item - 1].markAsDone();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      [\u2713] " + list[item - 1].description);
                    break;
            }
            System.out.println("________________________________________");
        }
    }
}