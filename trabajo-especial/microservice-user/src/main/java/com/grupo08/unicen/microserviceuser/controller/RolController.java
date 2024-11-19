package com.grupo08.unicen.microserviceuser.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo08.unicen.microserviceuser.dto.RolDto;

@RestController
@RequestMapping("/api/roles")
public class RolController {
/*    @Autowired
    RolService rolService;

    @PostMapping("")
    public ResponseEntity<RolDto> crearRol(@RequestBody RolDto rolDto) throws Exception {

            return rolService.save(rolDto);

    }*/

}