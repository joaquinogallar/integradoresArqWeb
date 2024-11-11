package com.grupo08.unicen.microservicemonopatin.exception;

public class StopNotFoundException extends RuntimeException {
    public StopNotFoundException(String stopId) {
        super("Stop with ID " + stopId + " not found");
    }
}
