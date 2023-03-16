package clone.twitter.model.exception;

import java.util.List;

public record RequestBodyError(CommonError Error, List<Object> DataError) {
}
