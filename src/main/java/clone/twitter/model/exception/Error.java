package clone.twitter.model.exception;

import java.util.Date;

public record Error(String Message, String Code, Integer StatusCode, Date TimeStamp) {
}
