import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public String get(int itemNumber) {
        return taskList.get(itemNumber).toString();
    }

    public int getSize() {
        return taskList.size();
    }

    public String getType(int itemNumber) {
        return taskList.get(itemNumber).getType();
    }

    public String getStatusIcon(int itemNumber) {
        return taskList.get(itemNumber).getStatusIcon();
    }

    public String getDescription(int itemNumber) {
        return taskList.get(itemNumber).description;
    }

    public String getDate(int itemNumber) {
        return taskList.get(itemNumber).date();
    }

    public ArrayList<String> find(String keyword) {
        int j = 1;
        ArrayList<String> results = new ArrayList<>();
        for (Task task : taskList) {
            if (task.description.contains(keyword.trim())) {
                results.add(j + ". " + task.toString());
                j++;
            }
        }
        return results;
    }

    public void markDone(int itemNumber) {
        taskList.get(itemNumber).markAsDone();
    }

    public void removeTask(int itemNumber) {
        taskList.remove(itemNumber);
    }

    public void addEvent(String description, LocalDateTime dateTime) {
        taskList.add(new Event(description.trim(), dateTime));
    }

    public void addDeadline(String description, LocalDateTime dateTime) {
        taskList.add(new Deadline(description.trim(), dateTime));
    }

    public void addTodo (String description) {
        taskList.add(new Todo(description.trim()));
    }
}
