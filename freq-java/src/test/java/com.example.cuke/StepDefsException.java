package com.example.cuke;

public class StepDefsException extends RuntimeException {
    public StepDefsException() {
    }

    public StepDefsException(String message) {
        super(message);
    }

    public StepDefsException(String message, Throwable cause) {
        super(message, cause);
    }

    public StepDefsException(Throwable cause) {
        super(cause);
    }

    public StepDefsException(String message,
                             Throwable cause,
                             boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
