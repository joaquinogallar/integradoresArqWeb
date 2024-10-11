package com.grupo08.unicen.trabajointegradortp3.exception;

public class CarreraNoEncontradaException extends RuntimeException {
    public CarreraNoEncontradaException(String message) {
        super(message);
        int errorCode = 404; // not found
    }
}
