package portal.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateParser {

    public static boolean isValid(String pattern, String value) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        try {
            LocalDate.parse(value, dtf);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static LocalDate parse(String pattern, String value) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDate.parse(value, dtf);
        } catch (Exception e) {
            return null;
        }
    }

}