import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    private Ui ui;

    public static void main(String[] args) {
        new Duke("[project_root]\\data\\duke.txt");
    }

    private Duke(String filepath) {
        ui = new Ui();
        ui.showLogo();
        ui.showLine();
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

            ui.showLine();
            switch (command) {
                case "bye":
                    exitCondition = true;
                    System.out.println("    Bye. Hope to see you again soon!");
                    break;
                case "list":
                    ui.showList(list);
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
                case "delete":
                    DeleteTask(list, description);
                    break;
                case "find":
                    ui.showFind(list, description);
                    break;
                default:
                    System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            ui.showLine();
        }
    }

    private void CreateTodo( ArrayList<Task> list, boolean markAsDone, String description) {
        try {
            list.add(new Todo(description.trim()));
            WriteToFile(list);
            ui.showCreateTask(list.get(Task.totalItems-1).toString(), Task.totalItems);
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description of a todo cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private void CreateDeadline(ArrayList<Task> list, boolean markAsDone, String description, String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(date.trim().substring(4), formatter);
            list.add(new Deadline(description.trim(), dateTime));
            WriteToFile(list);
            ui.showCreateTask(list.get(Task.totalItems-1).toString(), Task.totalItems);
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description/date of a deadline cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private void CreateEvent(ArrayList<Task> list, boolean markAsDone, String description, String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(date.trim().substring(4), formatter);
            list.add(new Event(description.trim(), dateTime));
            WriteToFile(list);
            ui.showCreateTask(list.get(Task.totalItems-1).toString(), Task.totalItems);
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description/date of an event cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private void MarkDone(ArrayList<Task>list, String description) {
        try {
            int item = Integer.parseInt(description.trim());
            list.get(item - 1).markAsDone();
            WriteToFile(list);
            ui.showDone(list.get(item - 1).description);
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("    OOPS!!! Please enter an item number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    OOPS!!! Item not found.");
        } catch (IOException e) {
            System.out.println("    Error. File not found.");
        }
    }

    private void DeleteTask(ArrayList<Task> list, String description) {
        try {
            int item = Integer.parseInt(description.trim());
            String task = list.get(item-1).toString();
            list.remove(item-1);
            Task.totalItems--;
            WriteToFile(list);
            ui.showDelete(task, list.size());
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("    OOPS!!! Please enter an item number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    OOPS!!! Item not found.");
        } catch (IOException e) {
            System.out.println("    Error. File not found.");
        }
    }

    private static void WriteToFile (ArrayList<Task> list) throws IOException {
        File file = new File("C:\\Users\\ryana\\Desktop\\2113_Project\\data\\duke.txt");
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < Task.totalItems; i++) {
            if (list.get(i) instanceof Todo) {
                bufferedWriter.write("T | " + list.get(i).getStatusIcon() + " | " + list.get(i).description + " | \n");
            } else if (list.get(i) instanceof Deadline) {
                bufferedWriter.write("D | " + list.get(i).getStatusIcon() + " | " + list.get(i).description + " | "
                        + list.get(i).date() + "\n");
            } else {
                bufferedWriter.write("E | " + list.get(i).getStatusIcon() + " | " + list.get(i).description + " | "
                        + list.get(i).date() + "\n");
            }
        }
        bufferedWriter.close();
    }

    private static void LoadFromFile (ArrayList<Task> list) throws IOException{
        File file = new File("C:\\Users\\ryana\\Desktop\\2113_Project\\data\\duke.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "|");
                String event = tokenizer.nextToken();
                String Done = tokenizer.nextToken();
                String description = tokenizer.nextToken();
                String date = tokenizer.nextToken();
                LocalDateTime dateTime = null;
                if (!(event.trim().equals("T"))) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    dateTime = LocalDateTime.parse(date.trim(), formatter);
                }
                switch (event.trim()) {
                    case "T":
                        list.add(new Todo (description.trim()));
                        if (Done.trim().equals("\u2713")) list.get(Task.totalItems-1).markAsDone();
                        break;
                    case "D":
                        list.add(new Deadline(description.trim(), dateTime));
                        if (Done.trim().equals("\u2713")) list.get(Task.totalItems-1).markAsDone();
                        break;
                    case "E":
                        list.add(new Event(description.trim(), dateTime));
                        if (Done.trim().equals("\u2713")) list.get(Task.totalItems-1).markAsDone();
                        break;
                    default:
                        System.out.println("    ERROR " + event);
                }
            }
        }
    }
}