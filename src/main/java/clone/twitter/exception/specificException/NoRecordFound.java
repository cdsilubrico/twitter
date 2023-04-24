package clone.twitter.exception.specificException;

import java.util.NoSuchElementException;

public class NoRecordFound extends NoSuchElementException {
    public NoRecordFound(final String msg) {
        super(msg);
    }
}
