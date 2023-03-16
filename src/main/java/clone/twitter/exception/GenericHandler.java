package clone.twitter.exception;

import clone.twitter.model.exception.CommonError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static clone.twitter.constant.ExceptionConstants.GenericExceptionConstants.UNKNOWN_ERROR;

@ControllerAdvice
@Order(value = 10)
public class GenericHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception exception) {

        logger.error(UNKNOWN_ERROR);

        return new ResponseEntity<>
                (new CommonError(exception.getMessage(), UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date()),
                        HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
