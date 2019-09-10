import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Command {
    private String type;
    private String description;
    private String date;
    private boolean isExit = false;

    public Command(String type, String description, String date) {
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (type) {
            case "bye":
                isExit = true;
                System.out.println("    Bye. Hope to see you again soon!");
                break;
            case "list":
                ui.showList(tasks);
                break;
            case "todo":
                CreateTodo(description, tasks, ui, storage);
                break;
            case "deadline":
                CreateDeadline(description, date, tasks, ui, storage);
                break;
            case "event":
                CreateEvent(description, date, tasks, ui, storage);
                break;
            case "done":
                MarkDone(description, tasks, ui, storage);
                break;
            case "delete":
                DeleteTask(description, tasks, ui, storage);
                break;
            case "find":
                ui.showFind(tasks.find(description));
                break;
            default:
                throw new DukeException("    OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void CreateTodo(String description, TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTodo(description.trim());
            storage.write(tasks);
            ui.showCreateTask(tasks.get(Task.totalItems - 1), Task.totalItems);
        } catch (NullPointerException e) {
            System.out.println("    OOPS!!! The description of a todo cannot be empty.");
        } catch (IOException e) {
            System.out.println("    Error in writing to file.");
        }
    }

    private void CreateDeadline(String description, String date, TaskList tasks, Ui ui, Storage storage)
            throws DukeException {
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
        } catch (Exception e) {
            throw new DukeException("Unknown error");
        }
    }

    private void CreateEvent(String description, String date, TaskList tasks, Ui ui, Storage storage)
            throws DukeException {
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
        } catch (Exception e) {
            throw new DukeException("Unknown error");
        }
    }

    private void MarkDone(String description, TaskList tasks, Ui ui, Storage storage)
            throws DukeException {
        try {
            int item = Parser.convertInt(description);
            tasks.markDone(item - 1);
            storage.write(tasks);
            ui.showDone(tasks.getDescription(item - 1));
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("    OOPS!!! Please enter an item number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    OOPS!!! Item not found.");
        } catch (IOException e) {
            System.out.println("    Error. File not found.");
        } catch (Exception e) {
            throw new DukeException("Unknown error");
        }
    }

    private void DeleteTask(String description, TaskList tasks, Ui ui, Storage storage)
            throws DukeException {
        try {
            int item = Parser.convertInt(description) - 1;
            String task = tasks.get(item);
            tasks.removeTask(item);
            storage.write(tasks);
            ui.showDelete(task, tasks.getSize());
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("    OOPS!!! Please enter an item number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    OOPS!!! Item not found.");
        } catch (IOException e) {
            System.out.println("    Error. File not found.");
        } catch (Exception e) {
            throw new DukeException("Unknown error");
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
