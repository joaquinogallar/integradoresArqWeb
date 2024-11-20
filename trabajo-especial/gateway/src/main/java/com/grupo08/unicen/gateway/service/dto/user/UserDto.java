package com.grupo08.unicen.gateway.service.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id ;
    
    @NotNull( message = "El usuario es un campo requerido." )
    @NotEmpty( message = "El usuario es un campo requerido." )
    private String username;

    @NotNull( message = "La contraseña es un campo requerido." )
    @NotEmpty( message = "La contraseña es un campo requerido." )
    private String password;

    @NotNull( message = "Los roles son un campo requerido." )
    @NotEmpty( message = "Los roles son un campo requerido." )
    private Set<String> authorities;
}
