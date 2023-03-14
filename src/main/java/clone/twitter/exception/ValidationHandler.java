package clone.twitter.exception;

import clone.twitter.model.exception.Error;
import clone.twitter.model.exception.DataError;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public final class ValidationHandler extends ResponseEntityExceptionHandler {
    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  @NonNull HttpHeaders httpHeaders, @NonNull HttpStatus httpStatus, @NonNull WebRequest webRequest) {


        final List<Object> dataErrors = exception.getFieldErrors()
                .stream()
                .map(dataError -> new DataError(dataError.getDefaultMessage(), dataError.getCode()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Error(httpStatus.value(), new Date(), dataErrors), httpStatus);
    }
}
