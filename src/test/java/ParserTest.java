import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    LocalDateTime dateTime1 = LocalDateTime.parse("02/08/2019 1400", formatter);

    @Test
    public void testInt1() {
        assertEquals(1, Parser.convertInt("1"));
    }

    @Test
    public void testInt2() {
        assertEquals(-500, Parser.convertInt("-500"));
    }

    @Test
    public void testInt3() {
        assertEquals(3, Parser.convertInt("3 "));
    }

    @Test
    public void testInt4() {
        assertEquals(0, Parser.convertInt("  0    "));
    }

    @Test
    public void testDate1() {
        assertEquals(dateTime1, Parser.convertDate(" 02/08/2019 1400    "));
    }
}
