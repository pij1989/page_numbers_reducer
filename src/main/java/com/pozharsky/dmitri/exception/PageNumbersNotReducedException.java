package com.pozharsky.dmitri.exception;

public class PageNumbersNotReducedException extends RuntimeException {

    public PageNumbersNotReducedException(String message) {
        super(message);
    }

    public PageNumbersNotReducedException(String message, Throwable cause) {
        super(message, cause);
    }
}
