package clone.twitter.exception.handler;

import clone.twitter.model.exception.Error;
import clone.twitter.util.exception.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static clone.twitter.constant.ExceptionConstants.GenericExceptionConstants.UNKNOWN_ERROR;
import static clone.twitter.constant.ExceptionConstants.GenericExceptionConstants.UNKNOWN_ERROR_CODE;

@ControllerAdvice
@Slf4j
public final class Generic extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGenericException() {

        log.error(UNKNOWN_ERROR);

        return ExceptionUtil.responseErrorUtil(UNKNOWN_ERROR, UNKNOWN_ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
