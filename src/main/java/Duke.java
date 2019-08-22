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
            System.out.println("________________________________________");

            if (string.equals("bye")) {
                exitCondition = true;
                System.out.println("    Bye. Hope to see you again soon!");
            } else if (string.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < items; i++) {
                    System.out.println("    "+ (i+1) + ". [" + list.get(i).getStatusIcon()+"] "
                            + list.get(i).description);
                }
            } else {
                StringTokenizer tokenizer = new StringTokenizer(string);
                if (tokenizer.nextToken().equals("done")) {
                    int item = Integer.parseInt(tokenizer.nextToken());
                    list.get(item-1).markAsDone();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      [\u2713] " + list.get(item-1).description);
                } else {
                    items++;
                    Task task = new Task(string);
                    list.add(task);
                    System.out.println("    added: "+string);
                }
            }
            System.out.println("________________________________________");
        }
    }
}
