package clone.twitter.exception.defaultHandler;

import org.springframework.dao.DataIntegrityViolationException;

public class DatabaseHandler extends DataIntegrityViolationException {
    public DatabaseHandler(String msg) {
        super(msg);
    }
}
