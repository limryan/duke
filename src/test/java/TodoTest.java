import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    private Todo todo = new Todo("my todo");

    @Test
    void toString1() {
        assertEquals("[T][\u2718] my todo", todo.toString());
    }

    @Test
    void getType1() {
        assertEquals("T", todo.getType());
    }

    @Test
    void getDescription1() {
        assertEquals("my todo", todo.description);
    }

    @Test
    void getStatusIcon1() {
        assertEquals("\u2718", todo.getStatusIcon());
        todo.markAsDone();
        assertEquals("\u2713", todo.getStatusIcon());
    }
}