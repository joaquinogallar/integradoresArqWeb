package com.grupo08.unicen.microserviceuser.controller;

import com.grupo08.unicen.microserviceuser.entity.UserEntity;
import com.grupo08.unicen.microserviceuser.service.UserEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/users")
public class UserEntityController {

    private UserEntityService userEntityService;

    // dependency injection
    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return userEntityService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable UUID userId) {
        return userEntityService.getUserById(userId);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserEntity newUser) {
        return userEntityService.createUser(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserEntity> deleteUserById(@PathVariable UUID userId) {
        return userEntityService.deleteUserById(userId);
    }
}
