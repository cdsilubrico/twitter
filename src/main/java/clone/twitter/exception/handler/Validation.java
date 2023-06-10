package clone.twitter.exception.handler;

import clone.twitter.model.exception.Error;
import clone.twitter.model.exception.DataError;
import clone.twitter.model.exception.RequestBodyError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

import static clone.twitter.constant.ExceptionConstants.RequestBodyExceptionConstants.*;

@Import({Database.class, Generic.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public final class Validation {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(final MethodArgumentNotValidException exception) {

        log.error(INVALID_INPUT_S);

        final List<Object> dataErrors = Collections.singletonList(exception.getFieldErrors()
                .stream()
                .map(dataError -> new DataError(dataError.getDefaultMessage(), dataError.getCode()))
                .toList());

        return new ResponseEntity<>
                (new RequestBodyError
                        (new Error
                                (INVALID_INPUT_S,
                                        INVALID_INPUT_S_CODE,
                                        new Date()),
                                dataErrors), HttpStatus.BAD_REQUEST);
    }
}
