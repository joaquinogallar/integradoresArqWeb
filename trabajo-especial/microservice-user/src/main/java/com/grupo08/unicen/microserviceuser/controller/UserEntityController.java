package com.grupo08.unicen.microserviceuser.controller;

import com.grupo08.unicen.microserviceuser.dto.UserEntityDto;
import com.grupo08.unicen.microserviceuser.model.JourneyDto;
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
    public ResponseEntity<List<UserEntityDto>> getAllUsers() {
        return userEntityService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntityDto> getUserById(@PathVariable UUID userId) {
        return userEntityService.getUserById(userId);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserEntityDto newUser) {
        return userEntityService.createUser(newUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserEntityDto> deleteUserById(@PathVariable UUID userId) {
        return userEntityService.deleteUserById(userId);
    }

    @PostMapping("/{userId}/accounts/{accountId}/qr/{monopatinId}")
    public ResponseEntity<JourneyDto> activateMonopatinByQr(@PathVariable UUID userId, @PathVariable UUID accountId, @PathVariable UUID monopatinId) {
        return userEntityService.activateMonopatinByQr(userId, monopatinId, accountId);
    }
}
