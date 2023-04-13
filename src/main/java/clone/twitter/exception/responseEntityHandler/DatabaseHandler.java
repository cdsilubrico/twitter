package clone.twitter.exception.responseEntityHandler;

import clone.twitter.model.exception.Error;
import clone.twitter.model.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.NoSuchElementException;

import static clone.twitter.constant.ExceptionConstants.DatabaseExceptionConstants.*;

@ControllerAdvice
@Order(2)
public final class DatabaseHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDuplicateDatabaseEntity() {

        LOGGER.error(DUPLICATE_USERNAME_OR_EMAIL);

        return new ResponseEntity<>(new DatabaseException
                (new Error(DUPLICATE_USERNAME_OR_EMAIL, DUPLICATE_USERNAME_OR_EMAIL_CODE,
                        HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date())),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleDatabaseElementNotFound() {

        LOGGER.error(USERNAME_OR_HANDLE_NOT_FOUND);

        return new ResponseEntity<>
                (new DatabaseException
                        (new Error(USERNAME_OR_HANDLE_NOT_FOUND, USERNAME_OR_HANDLE_NOT_FOUND_CODE, HttpStatus.BAD_REQUEST.value(), new Date())),
                        HttpStatus.BAD_REQUEST);
    }
}
