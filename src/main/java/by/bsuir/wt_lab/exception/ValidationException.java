package by.bsuir.wt_lab.exception;

public class ValidationException extends Exception {

    public ValidationException(String message) {
        super("Validation failed: " + message);
    }

}
