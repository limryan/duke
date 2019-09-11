import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private LocalDateTime dateTime = LocalDateTime.parse("28/02/2020 2359", formatter);
    private Event event = new Event("my event", dateTime);

    @Test
    void toString1() {
        assertEquals("[E][\u2718] my event (at: 28/02/2020 2359)", event.toString());
    }

    @Test
    void getDate1() {
        assertEquals("28/02/2020 2359", event.date());
    }

    @Test
    void getType1() {
        assertEquals("E", event.getType());
    }

    @Test
    void getDescription1() {
        assertEquals("my event", event.description);
    }

    @Test
    void getStatusIcon1() {
        assertEquals("\u2718", event.getStatusIcon());
        event.markAsDone();
        assertEquals("\u2713", event.getStatusIcon());
    }
}
