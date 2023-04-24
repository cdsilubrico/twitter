package clone.twitter.exception.specificException;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicateEntry extends DataIntegrityViolationException {
    public DuplicateEntry(final String msg) {
        super(msg);
    }
}
