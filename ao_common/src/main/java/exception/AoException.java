package exception;

public class AoException extends RuntimeException {

    public AoException() {
    }

    public AoException(String message) {
        super(message);
    }

    public AoException(String message, Throwable cause) {
        super(message, cause);
    }

    public AoException(Throwable cause) {
        super(cause);
    }

    public AoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
