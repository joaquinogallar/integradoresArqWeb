package com.grupo08.unicen.microserviceuser.service;

import com.grupo08.unicen.microserviceuser.entity.UserEntity;
import com.grupo08.unicen.microserviceuser.exception.UserNotFoundException;
import com.grupo08.unicen.microserviceuser.repository.UserEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

    // dependency injection
    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    // basic methods
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        try {
            List<UserEntity> users = userEntityRepository.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<UserEntity> getUserById(UUID userId) {
        UserEntity user = userEntityRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<String> createUser(UserEntity newUser) {
        try {
            userEntityRepository.save(newUser);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error: " + e);
        }
    }

    public ResponseEntity<UserEntity> deleteUserById(UUID userId) {
        UserEntity user = userEntityRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        userEntityRepository.delete(user);

        return ResponseEntity.ok(user);
    }

}
