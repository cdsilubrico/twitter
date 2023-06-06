package clone.twitter.exception.specific;

import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;

public class DuplicateEntry extends DataIntegrityViolationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DuplicateEntry(final String msg) {
        super(msg);
    }
}
