package com.grupo08.unicen.gateway.controller;

import com.grupo08.unicen.gateway.service.UserService;
import com.grupo08.unicen.gateway.service.dto.user.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserDto usuarioDto) throws Exception {
        final var id = userService.saveUser( usuarioDto );
        return new ResponseEntity<>( id, HttpStatus.CREATED );
    }
}
