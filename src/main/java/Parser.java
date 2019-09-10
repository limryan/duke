import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Class that parses a string and breaks it up or converts it to another format
 */
public class Parser {

    /**
     * Takes in the input from the user and tokenize it into different strings.
     * Packages them into a Command object.
     *
     * @param string the raw input from the user
     * @return command object with the type, description and date
     * @throws DukeException when there is a null string
     */
    public static Command parse(String string) throws DukeException {
        try {
            StringTokenizer tokenizer = new StringTokenizer(string);
            String type = tokenizer.nextToken();
            String description = null;
            String date = null;
            if (tokenizer.hasMoreTokens()) description = tokenizer.nextToken("/\n");
            if (tokenizer.hasMoreTokens()) date = tokenizer.nextToken("\n");
            return new Command(type, description, date);
        } catch (NoSuchElementException e) {
            throw new DukeException("    Please key in a command.");
        }
    }

    /**
     * Takes in a string that contains a date in the format 'dd/MM/yyyy HHmm'.
     * Outputs it as a LocalDateTime object
     *
     * @param string the date input
     * @return LocalDateTime object
     * @throws NumberFormatException when string is in the wrong format
     */
    public static LocalDateTime convertDate(String string) throws NumberFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(string.trim(), formatter);
    }

    /**
     * Takes in a string that contains just a number character and outputs it as
     * a integer.
     *
     * @param string containing a numerical character
     * @return int number
     * @throws NumberFormatException when string contains a non-numerical character
     */
    public static int convertInt(String string) throws NumberFormatException {
        return Integer.parseInt(string.trim());
    }
}
