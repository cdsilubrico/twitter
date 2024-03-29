package clone.twitter.constant;

public class ExceptionConstants {

    private ExceptionConstants() {
    }

    public static class GenericExceptionConstants {
        private GenericExceptionConstants() {

        }

        public static final String UNKNOWN_ERROR = "An unknown error occurred.";
        public static final String UNKNOWN_ERROR_CODE = "EC000";
    }

    public static class DatabaseExceptionConstants {
        private DatabaseExceptionConstants() {

        }

        public static final String DUPLICATE_USERNAME_OR_EMAIL = "Email or handle already exist.";
        public static final String DUPLICATE_USERNAME_OR_EMAIL_CODE = "EC001";
        public static final String NO_SUCH_RECORD_FOUND = "No such record found.";
        public static final String NO_SUCH_RECORD_FOUND_CODE = "EC003";
    }

    public static class RequestBodyExceptionConstants {
        private RequestBodyExceptionConstants() {

        }

        public static final String INVALID_INPUT_S = "Invalid input/s.";
        public static final String INVALID_INPUT_S_CODE = "EC002";
    }
}
