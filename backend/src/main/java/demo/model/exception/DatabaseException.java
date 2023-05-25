package demo.model.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
