import java.io.*;

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
}