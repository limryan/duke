import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Storage {

    public void write(ArrayList<Task> list, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
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

    public void load(ArrayList<Task> list, String filepath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
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
