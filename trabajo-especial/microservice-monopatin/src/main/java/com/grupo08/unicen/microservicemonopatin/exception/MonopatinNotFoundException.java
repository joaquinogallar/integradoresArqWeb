package com.grupo08.unicen.microservicemonopatin.exception;

public class MonopatinNotFoundException extends RuntimeException {
    public MonopatinNotFoundException(String monopatinId) {
        super("Monopatin with ID " + monopatinId + " not found");
    }
}
