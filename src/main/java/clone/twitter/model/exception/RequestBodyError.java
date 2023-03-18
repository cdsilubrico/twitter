package clone.twitter.model.exception;

import java.util.List;

public record RequestBodyError(clone.twitter.model.exception.Error Error, List<Object> DataError) {
}
