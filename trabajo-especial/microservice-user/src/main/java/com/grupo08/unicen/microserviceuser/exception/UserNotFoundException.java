package com.grupo08.unicen.microserviceuser.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("User with ID " + userId + " not found");
    }
}
