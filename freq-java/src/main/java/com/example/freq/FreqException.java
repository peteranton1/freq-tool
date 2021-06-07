package com.example.freq;

public class FreqException extends RuntimeException {
    public FreqException() {
    }

    public FreqException(String message) {
        super(message);
    }

    public FreqException(String message, Throwable cause) {
        super(message, cause);
    }

    public FreqException(Throwable cause) {
        super(cause);
    }

    public FreqException(String message,
                         Throwable cause,
                         boolean enableSuppression,
                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
