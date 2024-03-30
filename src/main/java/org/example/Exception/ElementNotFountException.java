package org.example.Exception;

public class ElementNotFountException extends RuntimeException {
    public ElementNotFountException() {
    }

    public ElementNotFountException(String message) {
        super(message);
    }

    public ElementNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementNotFountException(Throwable cause) {
        super(cause);
    }

    public ElementNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
