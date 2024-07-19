package com.backend.tokokantjil.exceptions;

public class NotEnoughInStockException extends RuntimeException {
    public NotEnoughInStockException() {
        super();
    }

    public NotEnoughInStockException(String message) {
        super(message);
    }
}
