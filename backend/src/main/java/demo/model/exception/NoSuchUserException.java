package demo.model.exception;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
