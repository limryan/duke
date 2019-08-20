import java.util.Scanner;

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

    public static void chatbot() {
        boolean exitCondition = false;
        Scanner scanner = new Scanner(System.in);
        String[] createlist = new String[100];
        int items = 0;

        while (!exitCondition) {
            String string = scanner.nextLine();
            if (string.equals("bye")) {
                exitCondition = true;
                System.out.println("________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("________________________________________");
            } else if (string.equals("list")) {
                System.out.println("________________________________________");
                for (int i = 0; i < items; i++) {
                    System.out.println("    "+ (i+1) +". "+createlist[i]);
                }
                System.out.println("________________________________________");
            } else {
                createlist[items] = string;
                items++;
                System.out.println("________________________________________");
                System.out.println("    added: "+string);
                System.out.println("________________________________________");
            }
        }
    }
}
