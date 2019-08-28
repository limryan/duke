import java.io.*;
import java.lang.reflect.Array;
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

        ChatBot();
    }

    private static void ChatBot() {
        boolean exitCondition = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        try {
            LoadFromFile(list);
        } catch (IOException e) {
            System.out.println("    Error in loading from file.");
        }

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
                    PrintList(list);
                    break;
                case "todo":
                    CreateTodo(list, false, description);
                    break;
                case "deadline":
                    CreateDeadline(list, false, description, date);
                    break;
                case "event":
                    CreateEvent(list, false, description, date);
                    break;
                case "done":
                    MarkDone(list, description);
                    break;
                default:
                    System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("________________________________________");
        }
    }

    private static void CreateTodo( ArrayList<Task> list, boolean markAsDone, String description) {
        try {
            list.add(new Todo(description.trim()));
            WriteToFile(list);
            System.out.println("    Got it. I've added this task:\n" + "      "
                    + list.get(Task.totalItems-1).toString());
            System.out.println("    Now you have " + Task.totalItems + " task(s) in the list.");
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description of a todo cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private static void CreateDeadline(ArrayList<Task> list, boolean markAsDone, String description, String date) {
        try {
            list.add(new Deadline(description.trim(), date.trim().substring(4)));
            WriteToFile(list);
            System.out.println("    Got it. I've added this task:\n" + "      "
                    + list.get(Task.totalItems-1).toString());
            System.out.println("    Now you have " + Task.totalItems + " task(s) in the list.");
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description/date of a deadline cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private static void CreateEvent(ArrayList<Task> list, boolean markAsDone, String description, String date) {
        try {
            list.add(new Event(description.trim(), date.trim().substring(4)));
            WriteToFile(list);
            System.out.println("    Got it. I've added this task:\n" + "      "
                    + list.get(Task.totalItems-1).toString());
            System.out.println("    Now you have " + Task.totalItems + " task(s) in the list.");
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description/date of an event cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private static void PrintList(ArrayList<Task> list) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < Task.totalItems; i++) {
            System.out.println("    " + (i + 1) + ". " + list.get(i).toString());
        }
    }

    private static void MarkDone(ArrayList<Task>list, String description) {
        try {
            int item = Integer.parseInt(description.trim());
            list.get(item - 1).markAsDone();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      [\u2713] " + list.get(item - 1).description);
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("    OOPS!!! Please enter an item number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    OOPS!!! Item not found.");
        }
    }

    private static void WriteToFile (ArrayList<Task> list) throws IOException {
        File file = new File("C:\\Users\\ryana\\Desktop\\2113_Project\\data\\duke.txt");
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < Task.totalItems; i++) {
            if (list.get(i) instanceof Todo) {
                bufferedWriter.write("T | " + list.get(i).isDone + " | " + list.get(i).description + " | \n");
            } else if (list.get(i) instanceof Deadline) {
                bufferedWriter.write("D | " + list.get(i).isDone + " | " + list.get(i).description + " | "
                        + list.get(i).date() + "\n");
            } else {
                bufferedWriter.write("E | " + list.get(i).isDone + " | " + list.get(i).description + " | "
                        + list.get(i).date() + "\n");
            }
        }
        bufferedWriter.close();
    }

    private static void LoadFromFile (ArrayList<Task> list) throws IOException{
        File file = new File("C:\\Users\\ryana\\Desktop\\2113_Project\\data\\duke.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "|");
                String event = tokenizer.nextToken();
                String Done = tokenizer.nextToken();
                String description = tokenizer.nextToken();
                String date = tokenizer.nextToken("\n");
                switch (event.trim()) {
                    case "T":
                        list.add(new Todo (description.trim()));
                        if (Done.trim().equals("\u2713")) list.get(Task.totalItems-1).markAsDone();
                        break;
                    case "D":
                        list.add(new Deadline(description.trim(), date.trim().substring(2)));
                        if (Done.trim().equals("\u2713")) list.get(Task.totalItems-1).markAsDone();
                        break;
                    case "E":
                        list.add(new Event(description.trim(), date.trim().substring(2)));
                        if (Done.trim().equals("\u2713")) list.get(Task.totalItems-1).markAsDone();
                        break;
                    default:
                        System.out.println("    ERROR " + event);
                }
            }
        }
    }
}