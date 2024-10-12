package com.grupo08.unicen.trabajointegradortp3.exception;

public class EstudianteCarreraNoEncontradoException extends RuntimeException {
    public EstudianteCarreraNoEncontradoException(String message) {
        super(message);
        int errorCode = 404; // not found
    }
}
