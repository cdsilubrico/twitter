package clone.twitter.model.exception;

import java.util.Date;
import java.util.List;

public record Error(Integer statusCode, Date timeStamp, List<Object> errorList) {
}
