import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Class the represents the aspects of saving and reading from file
 */
public class Storage {

    private String filepath;

    /**
     * Constructor to instantiate the storage object
     * @param filepath string that specifies which file to use
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Transfers the items/tasks in a task list into a text file and saves it.
     *
     * @param tasks the TaskList that contains all tasks
     * @throws IOException when file cannot be read
     */
    public void write(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < Task.totalItems; i++) {
            if (tasks.getType(i).equals("T")) {
                bufferedWriter.write("T | " + tasks.getStatusIcon(i) + " | " + tasks.getDescription(i) + " | \n");
            } else if (tasks.getType(i).equals("D")) {
                bufferedWriter.write("D | " + tasks.getStatusIcon(i) + " | " + tasks.getDescription(i) + " | "
                        + tasks.getDate(i) + "\n");
            } else {
                bufferedWriter.write("E | " + tasks.getStatusIcon(i) + " | " + tasks.getDescription(i) + " | "
                        + tasks.getDate(i) + "\n");
            }
        }
        bufferedWriter.close();
    }

    /**
     * Method that opens a text file, reads it and adds the information into a
     * new TaskList
     *
     * @return TaskList with the tasks in the text file
     * @throws IOException when file cannot be found/read
     */
    public ArrayList<Task> load() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            ArrayList<Task> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "|");
                String event = tokenizer.nextToken();
                String Done = tokenizer.nextToken();
                String description = tokenizer.nextToken();
                String date = tokenizer.nextToken();
                LocalDateTime dateTime = null;
                if (!(event.trim().equals("T"))) {
                    dateTime = Parser.convertDate(date);
                }
                switch (event.trim()) {
                    case "T":
                        list.add(new Todo(description.trim()));
                        if (Done.trim().equals("\u2713")) list.get(Task.totalItems - 1).markAsDone();
                        break;
                    case "D":
                        list.add(new Deadline(description.trim(), dateTime));
                        if (Done.trim().equals("\u2713")) list.get(Task.totalItems - 1).markAsDone();
                        break;
                    case "E":
                        list.add(new Event(description.trim(), dateTime));
                        if (Done.trim().equals("\u2713")) list.get(Task.totalItems - 1).markAsDone();
                        break;
                    default:
                        System.out.println("    ERROR " + event);
                }
            }
            return list;
        }
    }
}
