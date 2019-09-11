import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private LocalDateTime dateTime1 = LocalDateTime.parse("12/12/2019 1400", formatter);
    private LocalDateTime dateTime2 = LocalDateTime.parse("23/09/2019 1000", formatter);
    private TaskList taskList = new TaskList();
    private void addTasks() {
        taskList.addTodo("read book");
        taskList.addDeadline("return book", dateTime1);
        taskList.addEvent("lecture", dateTime2);
    }

    @Test
    void TaskListAdd1() {
        addTasks();
        assertEquals("read book", taskList.getDescription(0));
        assertEquals("return book", taskList.getDescription(1));
        assertEquals("lecture", taskList.getDescription(2));
    }

    @Test
    void TaskListFind2() {
        addTasks();
        ArrayList<String> list = new ArrayList<>();
        list.add("1. [T][\u2718] read book");
        list.add("2. [D][\u2718] return book (by: 12/12/2019 1400)");
        assertEquals(list, taskList.find("book"));
    }

    @Test
    void TaskListExceptions1() {
        addTasks();
        assertThrows(IndexOutOfBoundsException.class, () -> {taskList.get(3);});
        assertThrows(IndexOutOfBoundsException.class, () -> {taskList.get(-1);});
    }
}
