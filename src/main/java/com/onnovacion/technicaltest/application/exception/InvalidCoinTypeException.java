package com.onnovacion.technicaltest.application.exception;

public class InvalidCoinTypeException extends RuntimeException {
    public InvalidCoinTypeException(String msg) {
        super(msg);
    }
}
