package com.grupo08.unicen.trabajointegradortp3.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String message) {
        super(message);
        int errorCode = 404; // not found
    }
}
