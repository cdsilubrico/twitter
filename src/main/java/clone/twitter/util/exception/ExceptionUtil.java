package clone.twitter.util.exception;

import clone.twitter.model.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ExceptionUtil {
    public static ResponseEntity<Object> responseErrorUtil(final String message, final String code, final HttpStatus statusCode) {
        return new ResponseEntity<>(new Error(message, code, new Date()), statusCode);
    }
}
