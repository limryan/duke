import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static LocalDateTime convertDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(string.trim(), formatter);
    }

    public static int convertInt(String string) throws NumberFormatException{
        return Integer.parseInt(string.trim());
    }
}
