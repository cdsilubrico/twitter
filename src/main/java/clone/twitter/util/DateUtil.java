package clone.twitter.util;

import java.time.LocalDate;

public class DateUtil {
    private DateUtil() {
    }

    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }
}
