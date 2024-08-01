package com.backend.tokokantjil.exceptions;

public class EnumerationValueIsUnprocessableException extends RuntimeException {
    public EnumerationValueIsUnprocessableException() {
        super();
    }

    public EnumerationValueIsUnprocessableException(String message) {
        super(message);
    }
}
