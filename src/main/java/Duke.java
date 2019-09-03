import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    private Ui ui;
    private Storage storage;

    public static void main(String[] args) {
        new Duke("C:\\Users\\ryana\\Desktop\\2113_Project\\data\\duke.txt");
    }

    private Duke(String filepath) {
        ui = new Ui();
        storage = new Storage();
        ui.showLogo();
        ui.showLine();
        boolean exitCondition = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        try {
            storage.load(list, filepath);
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
                    CreateTodo(list, false, description, filepath);
                    break;
                case "deadline":
                    CreateDeadline(list, false, description, date, filepath);
                    break;
                case "event":
                    CreateEvent(list, false, description, date, filepath);
                    break;
                case "done":
                    MarkDone(list, description, filepath);
                    break;
                case "delete":
                    DeleteTask(list, description, filepath);
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

    private void CreateTodo( ArrayList<Task> list, boolean markAsDone, String description, String filepath) {
        try {
            list.add(new Todo(description.trim()));
            storage.write(list, filepath);
            ui.showCreateTask(list.get(Task.totalItems-1).toString(), Task.totalItems);
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description of a todo cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private void CreateDeadline(ArrayList<Task> list, boolean markAsDone, String description, String date, String filepath) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(date.trim().substring(4), formatter);
            list.add(new Deadline(description.trim(), dateTime));
            storage.write(list, filepath);
            ui.showCreateTask(list.get(Task.totalItems-1).toString(), Task.totalItems);
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description/date of a deadline cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private void CreateEvent(ArrayList<Task> list, boolean markAsDone, String description, String date, String filepath) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(date.trim().substring(4), formatter);
            list.add(new Event(description.trim(), dateTime));
            storage.write(list, filepath);
            ui.showCreateTask(list.get(Task.totalItems-1).toString(), Task.totalItems);
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description/date of an event cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private void MarkDone(ArrayList<Task>list, String description, String filepath) {
        try {
            int item = Integer.parseInt(description.trim());
            list.get(item - 1).markAsDone();
            storage.write(list, filepath);
            ui.showDone(list.get(item - 1).description);
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("    OOPS!!! Please enter an item number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    OOPS!!! Item not found.");
        } catch (IOException e) {
            System.out.println("    Error. File not found.");
        }
    }

    private void DeleteTask(ArrayList<Task> list, String description, String filepath) {
        try {
            int item = Integer.parseInt(description.trim());
            String task = list.get(item-1).toString();
            list.remove(item-1);
            Task.totalItems--;
            storage.write(list, filepath);
            ui.showDelete(task, list.size());
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("    OOPS!!! Please enter an item number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    OOPS!!! Item not found.");
        } catch (IOException e) {
            System.out.println("    Error. File not found.");
        }
    }
}