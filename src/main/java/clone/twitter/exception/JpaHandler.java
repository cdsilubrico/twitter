package clone.twitter.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class JpaHandler {
    public ResponseEntity<Object> handleDuplicateEntryException(EntityExistsException ex) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if (rootCause instanceof ConstraintViolationException) {
            String errorMessage = "Duplicate entry for a unique field. Please use a different value.";
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
