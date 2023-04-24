package clone.twitter.exception.handler;

import clone.twitter.util.exception.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static clone.twitter.constant.ExceptionConstants.GenericExceptionConstants.UNKNOWN_ERROR;
import static clone.twitter.constant.ExceptionConstants.GenericExceptionConstants.UNKNOWN_ERROR_CODE;

@ControllerAdvice
public final class Generic extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Generic.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException() {

        LOGGER.error(UNKNOWN_ERROR);

        return ExceptionUtil.responseErrorUtil(UNKNOWN_ERROR, UNKNOWN_ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
