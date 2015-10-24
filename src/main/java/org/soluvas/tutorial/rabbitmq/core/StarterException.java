package org.soluvas.tutorial.rabbitmq.core;

public class StarterException extends RuntimeException {

    public StarterException() {
    }

    public StarterException(String message) {
        super(message);
    }

    public StarterException(String message, Throwable cause) {
        super(message, cause);
    }

    public StarterException(Throwable cause) {
        super(cause);
    }

    public StarterException(Throwable cause, String format, Object... args) {
        super(String.format(format, args), cause);
    }

    public StarterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
