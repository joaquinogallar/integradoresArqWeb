package com.grupo08.unicen.microserviceuser.service;

import com.grupo08.unicen.microserviceuser.dto.UserEntityDto;
import com.grupo08.unicen.microserviceuser.entity.UserEntity;
import com.grupo08.unicen.microserviceuser.exception.UserNotFoundException;
import com.grupo08.unicen.microserviceuser.repository.UserEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ResponseEntity<List<UserEntityDto>> getAllUsers() {
        try {
            List<UserEntity> users = userEntityRepository.findAll();
            List<UserEntityDto> userDtos = new ArrayList<>();
            users.forEach(u -> userDtos.add(new UserEntityDto(u)));
            return ResponseEntity.ok(userDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<UserEntityDto> getUserById(UUID userId) {
        UserEntity user = userEntityRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        UserEntityDto userDto = new UserEntityDto(user);
        return ResponseEntity.ok(userDto);
    }

    public ResponseEntity<String> createUser(UserEntityDto newUser) {
        try {
            userEntityRepository.save(new UserEntity(newUser));
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error: " + e);
        }
    }

    public ResponseEntity<UserEntityDto> deleteUserById(UUID userId) {
        UserEntity user = userEntityRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        UserEntityDto userDto = new UserEntityDto(user);
        userEntityRepository.delete(user);

        return ResponseEntity.ok(userDto);
    }

}
