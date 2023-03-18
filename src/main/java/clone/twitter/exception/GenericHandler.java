package clone.twitter.exception;

import clone.twitter.model.exception.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static clone.twitter.constant.ExceptionConstants.GenericExceptionConstants.UNKNOWN_ERROR;

public final class GenericHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GenericHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception exception) {

        logger.error(UNKNOWN_ERROR);

        return new ResponseEntity<>
                (new Error(exception.getMessage(), UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date()),
                        HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
