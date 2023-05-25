package demo.model.exception;

public class NoSuchOfferException extends RuntimeException {
    public NoSuchOfferException(String message, Throwable cause) {
        super(message, cause);
    }
}
