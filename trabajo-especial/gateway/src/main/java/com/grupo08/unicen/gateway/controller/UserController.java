package com.grupo08.unicen.gateway.controller;

import com.grupo08.unicen.gateway.service.UserService;
import com.grupo08.unicen.gateway.service.dto.user.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserDto userDto) {
        final var id = userService.saveUser( userDto );
        return new ResponseEntity<>( id, HttpStatus.CREATED );
    }

    @GetMapping
    public String getAllUsers() {
        return "TESTEO";
    }
}
