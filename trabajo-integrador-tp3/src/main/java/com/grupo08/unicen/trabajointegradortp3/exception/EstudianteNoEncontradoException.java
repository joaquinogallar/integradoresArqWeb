package com.grupo08.unicen.trabajointegradortp3.exception;

public class EstudianteNoEncontradoException extends RuntimeException {
    public EstudianteNoEncontradoException(String message) {
        super(message);
        int errorCode = 404; // not found
    }
}
