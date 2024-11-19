package com.grupo08.unicen.microserviceuser.controller;

import com.grupo08.unicen.microserviceuser.dto.UserEntityDto;
import com.grupo08.unicen.microserviceuser.model.JourneyDto;
import com.grupo08.unicen.microserviceuser.model.MonopatinDto;
import com.grupo08.unicen.microserviceuser.service.UserEntityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/users")
@Tag(name = "User Entity", description = "Operaciones relacionadas con la entidad de usuario.")
public class UserEntityController {

    @Autowired
    UserEntityService userEntityService;

    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Devuelve una lista de todos los usuarios registrados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuarios obtenidos correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = UserEntityDto.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<UserEntityDto>> getAllUsers() {
        try {
            return ResponseEntity.ok(userEntityService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(
            summary = "Obtener un usuario por ID",
            description = "Devuelve los detalles de un usuario específico basado en su UUID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario obtenido correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserEntityDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserEntityDto> getUserById(@PathVariable UUID userId) {
        try {
            return ResponseEntity.ok(userEntityService.getUserById(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Crea un nuevo usuario a partir de los datos proporcionados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuario creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error en la solicitud",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserEntityDto newUser) {
        try {
            return ResponseEntity.ok(userEntityService.createUser(newUser));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @Operation(
            summary = "Eliminar un usuario",
            description = "Elimina un usuario basado en su UUID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario eliminado correctamente",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json")
            )
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserEntityDto> deleteUserById(@PathVariable UUID userId) {
        try {
            return ResponseEntity.ok(userEntityService.deleteUserById(userId));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @Operation(
            summary = "Activar monopatín mediante QR",
            description = "Activa un monopatín específico asociado a un usuario mediante su ID y el ID de cuenta."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Monopatín activado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JourneyDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Monopatín o usuario no encontrado",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/{userId}/accounts/{accountId}/qr/{monopatinId}")
    public ResponseEntity<JourneyDto> activateMonopatinByQr(
            @PathVariable UUID userId,
            @PathVariable UUID accountId,
            @PathVariable UUID monopatinId) {
        try {
            return ResponseEntity.ok(userEntityService.activateMonopatinByQr(userId, monopatinId, accountId));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @Operation(
            summary = "Obtener monopatines cercanos",
            description = "Obtiene una lista de monopatines cercanos al usuario dentro del rango especificado."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Monopatines cercanos obtenidos correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = MonopatinDto.class)
                    )
            )
    })
    @GetMapping("/nearMonopatines/{id}/{rangoMetros}")
    public ResponseEntity<List<MonopatinDto>> getNearMonopatines(@PathVariable UUID id, @PathVariable int rangoMetros){
        try {
            return ResponseEntity.ok(userEntityService.getNearMonopatines(id, rangoMetros));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @Operation(
            summary = "Editar datos de un usuario",
            description = "Permite editar la información de un usuario específico."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario editado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserEntityDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error en la solicitud",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PutMapping("/{userId}")
    public ResponseEntity<UserEntityDto> editUser(@PathVariable UUID userId, @RequestBody UserEntityDto u){
        return ResponseEntity.ok(userEntityService.editUser(userId, u));
    }

    @PutMapping("/{userId}/accounts/{accountId}")
    public ResponseEntity<String> addAccount(@PathVariable UUID userId, @PathVariable UUID accountId){
        try {
            userEntityService.addAccount(userId, accountId);
            return ResponseEntity.ok("Account added");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}

