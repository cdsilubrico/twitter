package clone.twitter.exception;

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

import static clone.twitter.constant.ExceptionConstants.DatabaseExceptionConstants.*;

@ControllerAdvice
@Order(value = 2)
public final class DatabaseHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDatabaseException() {

        logger.error(DUPLICATE_USERNAME_OR_EMAIL);

        return new ResponseEntity<>(new DatabaseException
                (new Error(DUPLICATE_USERNAME_OR_EMAIL, DUPLICATE_USERNAME_OR_EMAIL_CODE,
                        HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date())), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
