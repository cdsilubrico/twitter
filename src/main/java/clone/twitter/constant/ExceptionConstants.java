package clone.twitter.constant;

public class ExceptionConstants {
    public static class GenericExceptionConstants {
        public static final String UNKNOWN_ERROR = "An unknown error occurred.";
        public static final String UNKNOWN_ERROR_CODE = "EC000";
    }
    public static class DatabaseExceptionConstants {
        public static final String DUPLICATE_USERNAME_OR_EMAIL = "Username or email already exist.";
        public static final String DUPLICATE_USERNAME_OR_EMAIL_CODE = "EC001";
    }

    public static class RequestBodyExceptionConstants {
        public static final String INVALID_INPUT_S = "Invalid input/s.";
        public static final String INVALID_INPUT_S_CODE = "EC002";
    }
}