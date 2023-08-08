package clone.twitter.util;

import org.slf4j.Logger;

public class LoggerUtil {

    private LoggerUtil() {
    }


    public static void logInfoUtil(final Logger log, final String message) {
        if (log.isInfoEnabled()) {
            log.info(message);
        }
    }

    public static void logErrorUtil(final Logger log, final String message) {
        if(log.isErrorEnabled()) {
            log.error(message);
        }
    }

}
