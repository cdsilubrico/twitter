package clone.twitter.model.exception;

import java.util.List;

public record RequestBodyError(Error Error, List<Object> DataError) {
}
