import javax.swing.*;
import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public static void main(String[] args) {
        new Duke("C:\\Users\\ryana\\Desktop\\2113_Project\\data\\duke.txt").run();
    }

    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.read();
            StringTokenizer tokenizer = new StringTokenizer(fullCommand);
            String command = tokenizer.nextToken();
            String description = null;
            String date = null;
            if (tokenizer.hasMoreTokens()) description = tokenizer.nextToken("/\n");
            if (tokenizer.hasMoreTokens()) date = tokenizer.nextToken("\n");

            ui.showLine();
            switch (command) {
                case "bye":
                    isExit = true;
                    System.out.println("    Bye. Hope to see you again soon!");
                    break;
                case "list":
                    ui.showList(tasks);
                    break;
                case "todo":
                    CreateTodo(description);
                    break;
                case "deadline":
                    CreateDeadline(description, date);
                    break;
                case "event":
                    CreateEvent(description, date);
                    break;
                case "done":
                    MarkDone(description);
                    break;
                case "delete":
                    DeleteTask(description);
                    break;
                case "find":
                    ui.showFind(tasks.find(description));
                    break;
                default:
                    System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            ui.showLine();
        }
    }

    private void CreateTodo(String description) {
        try {
            tasks.addTodo(description.trim());
            storage.write(tasks);
            ui.showCreateTask(tasks.get(Task.totalItems-1), Task.totalItems);
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description of a todo cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private void CreateDeadline(String description, String date) {
        try {
            LocalDateTime dateTime = Parser.convertDate(date.substring(4));
            tasks.addDeadline(description, dateTime);
            storage.write(tasks);
            ui.showCreateTask(tasks.get(Task.totalItems - 1), Task.totalItems);
        } catch (DateTimeException e) {
            System.out.println("    Please enter date and time in the format: dd/mm/yyyy HHmm.");
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description/date of a deadline cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private void CreateEvent(String description, String date) {
        try {
            LocalDateTime dateTime = Parser.convertDate(date.substring(4));
            tasks.addEvent(description, dateTime);
            storage.write(tasks);
            ui.showCreateTask(tasks.get(Task.totalItems - 1), Task.totalItems);
        } catch (DateTimeException e) {
            System.out.println("    Please enter date & time in the format: dd/mm/yyyy HHmm.");
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description/date of an event cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private void MarkDone(String description) {
        try {
            int item = Parser.convertInt(description);
            tasks.markDone(item-1);
            storage.write(tasks);
            ui.showDone(tasks.getDescription(item-1));
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("    OOPS!!! Please enter an item number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    OOPS!!! Item not found.");
        } catch (IOException e) {
            System.out.println("    Error. File not found.");
        }
    }

    private void DeleteTask(String description) {
        try {
            int item = Parser.convertInt(description);
            String task = tasks.get(item-1);
            tasks.removeTask(item-1);
            Task.totalItems--;
            storage.write(tasks);
            ui.showDelete(task, tasks.getSize());
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("    OOPS!!! Please enter an item number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    OOPS!!! Item not found.");
        } catch (IOException e) {
            System.out.println("    Error. File not found.");
        }
    }
}