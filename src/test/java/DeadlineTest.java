import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private LocalDateTime dateTime = LocalDateTime.parse("12/12/2019 1400", formatter);
    private Deadline deadline = new Deadline("my task", dateTime);

    @Test
    void toString1() {
        assertEquals("[D][\u2718] my task (by: 12/12/2019 1400)", deadline.toString());
    }

    @Test
    void getDate1() {
        assertEquals("12/12/2019 1400", deadline.date());
    }

    @Test
    void getType1() {
        assertEquals("D", deadline.getType());
    }

    @Test
    void getDescription1() {
        assertEquals("my task", deadline.description);
    }

    @Test
    void getStatusIcon1() {
        assertEquals("\u2718",deadline.getStatusIcon());
    }
}
