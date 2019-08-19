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
        while (!exitCondition) {
            String string = scanner.nextLine();
            if (string.equals("bye")) {
                exitCondition = true;
                System.out.println("________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("________________________________________");
            } else {
                System.out.println("________________________________________");
                System.out.println("    "+string);
                System.out.println("________________________________________");
            }
        }
    }
}
