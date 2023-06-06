package clone.twitter.exception.specific;

import java.io.Serial;
import java.util.NoSuchElementException;

public class NoRecordFound extends NoSuchElementException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NoRecordFound(final String msg) {
        super(msg);
    }
}
