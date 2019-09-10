import java.io.IOException;

/**
 * Main class for the Duke program
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public static void main(String[] args) {
        new Duke("C:\\Users\\ryana\\Desktop\\2113_Project\\data\\duke.txt").run();
    }

    /**
     * Initialises the Duke chat bot. Creates an object UI for user interactions,
     * storage object to load and write information from a specified location.
     * Creates a TaskList object to store data. If information from the specified
     * file cannot be found, an empty TaskList will be created.
     *
     * @param filePath string that contains the location to the data file
     */
    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the operation of the chat bot. It will read in input from the user,
     * complete the specified command, and output requested data or confirmation
     * that the task is complete. Will continue in an infinite loop until the
     * isExit condition is true.
     */
    private void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.read();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

//    @Override
//    public void start(Stage stage) {
//        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//    }
}