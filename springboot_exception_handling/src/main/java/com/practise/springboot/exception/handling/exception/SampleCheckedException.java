package com.practise.springboot.exception.handling.exception;

public class SampleCheckedException extends Exception {
    private static final long serialVersionUID = 1L;

    public SampleCheckedException(String message) {
        super(message);
    }

    public SampleCheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SampleCheckedException(Throwable cause) {
        super(cause);
    }

    public SampleCheckedException() {
        super();
    }
}
