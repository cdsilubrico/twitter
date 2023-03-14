package clone.twitter.model.exception;

import java.util.Date;

public record CommonError(String Message, String Code, Integer StatusCode, Date TimeStamp) {
}
