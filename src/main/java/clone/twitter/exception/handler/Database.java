package clone.twitter.exception.handler;

import clone.twitter.exception.specific.DuplicateEntry;
import clone.twitter.exception.specific.NoRecordFound;
import clone.twitter.model.exception.Error;
import clone.twitter.util.exception.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static clone.twitter.constant.ExceptionConstants.DatabaseExceptionConstants.*;

@ControllerAdvice
@Order(2)
@Slf4j
public final class Database extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicateEntry.class)
    public ResponseEntity<Error> handleDuplicateEntry() {

        log.error(DUPLICATE_USERNAME_OR_EMAIL);

        return ExceptionUtil.responseErrorUtil(DUPLICATE_USERNAME_OR_EMAIL, DUPLICATE_USERNAME_OR_EMAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordFound.class)
    public ResponseEntity<Error> handleNoElementFound() {

        log.error(NO_SUCH_RECORD_FOUND);

        return ExceptionUtil.responseErrorUtil(NO_SUCH_RECORD_FOUND, NO_SUCH_RECORD_FOUND_CODE, HttpStatus.BAD_REQUEST);
    }
}
