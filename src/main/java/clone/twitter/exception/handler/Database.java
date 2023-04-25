package clone.twitter.exception.handler;

import clone.twitter.exception.specificException.DuplicateEntry;
import clone.twitter.exception.specificException.NoRecordFound;
import clone.twitter.model.exception.Error;
import clone.twitter.util.exception.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static clone.twitter.constant.ExceptionConstants.DatabaseExceptionConstants.*;

@ControllerAdvice
@Order(2)
public final class Database extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);

    @ExceptionHandler(DuplicateEntry.class)
    public ResponseEntity<Error> handleDuplicateEntry() {

        LOGGER.error(DUPLICATE_USERNAME_OR_EMAIL);

        return ExceptionUtil.responseErrorUtil(DUPLICATE_USERNAME_OR_EMAIL, DUPLICATE_USERNAME_OR_EMAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordFound.class)
    public ResponseEntity<Error> handleNoElementFound() {

        LOGGER.error(NO_SUCH_RECORD_FOUND);

        return ExceptionUtil.responseErrorUtil(NO_SUCH_RECORD_FOUND, NO_SUCH_RECORD_FOUND_CODE, HttpStatus.BAD_REQUEST);
    }
}
