import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class Parser {

    public static Command parse(String string) {
        StringTokenizer tokenizer = new StringTokenizer(string);
        String type = tokenizer.nextToken();
        String description = null;
        String date = null;
        if (tokenizer.hasMoreTokens()) description = tokenizer.nextToken("/\n");
        if (tokenizer.hasMoreTokens()) date = tokenizer.nextToken("\n");
        return new Command(type, description, date);
    }

    public static LocalDateTime convertDate(String string) throws NumberFormatException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(string.trim(), formatter);
    }

    public static int convertInt(String string) throws NumberFormatException{
        return Integer.parseInt(string.trim());
    }
}
