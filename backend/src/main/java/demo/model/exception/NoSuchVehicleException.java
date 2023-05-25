package demo.model.exception;

public class NoSuchVehicleException extends RuntimeException {
    public NoSuchVehicleException(String message, Throwable cause) {
        super(message, cause);
    }
}
