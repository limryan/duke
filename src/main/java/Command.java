import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Class that packages the different parts of a command
 */
public class Command {
    private String type;
    private String description;
    private String date;
    private boolean isExit = false;

    /**
     * Constructor to create a command object. Includes the type of command,
     * the name/description of task, and the date (if any)
     *
     * @param type        string that contains the command type
     * @param description string that contains the name or description of the
     *                    command
     * @param date        string that contains the date portion of the input
     */
    public Command(String type, String description, String date) {
        this.type = type;
        this.description = description;
        this.date = date;
    }

    /**
     * Starts the execution process of the chatbot when a command is entered. The
     * command is parsed using Parser to determine the steps Duke is required to
     * perform.
     *
     * @param tasks   An object of TaskList containing a list of tasks
     * @param ui      An object that handles all interactions with the user
     * @param storage An object that handles writing to file and loading from file
     * @throws DukeException when there is an unknown command
     */
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

    /**
     * When a user enters a todo command, this method will be called and a new task
     * object will be created (of todo type)
     *
     * @param description the string that identifies the task
     * @param tasks the TaskLists that contains the list of tasks
     * @param ui An object that handles interactions with the user
     * @param storage An object that handles writing to file and loading from file
     */
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

    /**
     * When a user enters a deadline command, this method will be called and a new task
     * object will be created (of deadline type).
     *
     * @param description the string that identifies the task
     * @param date the deadline of the task (in dd/mm/yyyy HHmm format)
     * @param tasks the TaskLists that contains the list of tasks
     * @param ui An object that handles interactions with the user
     * @param storage An object that handles writing to file and loading from file
     * @throws DukeException if there is an unknown error
     */
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

    /**
     * When a user enters a event command, this method will be called and a new task
     * object will be created (of event type).
     *
     * @param description the string that identifies the task
     * @param date the deadline of the task (in dd/mm/yyyy HHmm format)
     * @param tasks the TaskLists that contains the list of tasks
     * @param ui An object that handles interactions with the user
     * @param storage An object that handles writing to file and loading from file
     * @throws DukeException if there is an unknown error
     */
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

    /**
     * When a user enters a done command, this method will be called. The task item
     * number will be used to get the task from the TaskList and the task will be
     * set as done. File will then be overwritten with the new information. The user
     * will then be informed that the item has been marked as done.
     *
     * @param description the string that identifies the task
     * @param tasks the TaskLists that contains the list of tasks
     * @param ui An object that handles interactions with the user
     * @param storage An object that handles writing to file and loading from file
     * @throws DukeException if there is an unknown error
     */
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

    /**
     * When a user enters a delete command, this method will be called. The task item
     * number will be used to get the task from the TaskList and the task will be
     * removed from the list. File will then be overwritten with the new information.
     * The user will then be informed that the item has been deleted along with the
     * number of items left in the list.
     *
     * @param description the string that identifies the task
     * @param tasks the TaskLists that contains the list of tasks
     * @param ui An object that handles interactions with the user
     * @param storage An object that handles writing to file and loading from file
     * @throws DukeException if there is an unknown error
     */
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

    /**
     * This method returns the isExit condition to indicate if Duke should
     * terminate.
     *
     * @return the isExit boolean
     */
    public boolean isExit() {
        return isExit;
    }
}
