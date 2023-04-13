package clone.twitter.exception.defaultHandler;

import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serializable;

public class DatabaseHandler extends DataIntegrityViolationException implements Serializable {
    public DatabaseHandler(final String msg) {
        super(msg);
    }
}
