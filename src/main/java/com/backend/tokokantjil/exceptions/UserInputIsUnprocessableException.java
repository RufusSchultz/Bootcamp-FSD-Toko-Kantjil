package com.backend.tokokantjil.exceptions;

public class UserInputIsUnprocessableException extends RuntimeException {
    public UserInputIsUnprocessableException() {
        super();
    }

    public UserInputIsUnprocessableException(String message) {
        super(message);
    }
}
