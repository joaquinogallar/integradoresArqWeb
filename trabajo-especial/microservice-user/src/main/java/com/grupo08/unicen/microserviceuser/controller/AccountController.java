package com.grupo08.unicen.microserviceuser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo08.unicen.microserviceuser.dto.AccountDto;
import com.grupo08.unicen.microserviceuser.service.AccountService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/accounts")
@Tag(name = "Accounts", description = "Endpoints para gestionar cuentas de usuario")
public class AccountController {

    @Autowired
    AccountService accountService;

    // Basic methods
    @Operation(
            summary = "Obtener todas las cuentas",
            description = "Devuelve una lista de todas las cuentas registradas."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cuentas obtenidas correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = AccountDto.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @Operation(
            summary = "Obtener una cuenta por ID",
            description = "Devuelve los detalles de una cuenta específica basada en su UUID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cuenta obtenida correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cuenta no encontrada",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@Parameter(description = "ID de la cuenta", example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable UUID accountId) {
        return accountService.getAccountById(accountId);
    }

    @Operation(
            summary = "Crear una nueva cuenta",
            description = "Crea una nueva cuenta a partir de los datos proporcionados.",
            requestBody = @RequestBody(
                    description = "Datos de la cuenta a crear",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cuenta creada exitosamente",
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
    public ResponseEntity<String> createAccount(
            @RequestBody @Schema(description = "Datos de la cuenta a crear", implementation = AccountDto.class) AccountDto newAccount) {
        try {
            return ResponseEntity.ok(accountService.createAccount(newAccount));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @Operation(
            summary = "Eliminar una cuenta",
            description = "Elimina una cuenta basada en su UUID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cuenta eliminada correctamente",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cuenta no encontrada",
                    content = @Content(mediaType = "application/json")
            )
    })
    @DeleteMapping("/{accountId}")
    public ResponseEntity<AccountDto> deleteUserById(@Parameter(description = "ID de la cuenta a eliminar", example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable UUID accountId) {
        return accountService.deleteAccountById(accountId);
    }

    // Custom methods
    @Operation(
            summary = "Agregar dinero a una cuenta",
            description = "Permite agregar una cantidad específica de dinero a una cuenta basada en su UUID.",
            requestBody = @RequestBody(
                    description = "Cantidad de dinero a agregar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Double.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Dinero agregado exitosamente",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error en la solicitud",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PutMapping("/{accountId}/add-money")
    public ResponseEntity<String> addMoney(
            @Parameter(description = "ID de la cuenta", example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable UUID accountId,
            @RequestParam @Schema(description = "Cantidad a agregar", example = "100.0") Double quantity) {
        return accountService.addMoney(accountId, quantity);
    }

    @Operation(
            summary = "Deshabilitar una cuenta",
            description = "Deshabilita una cuenta basada en su UUID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cuenta deshabilitada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cuenta no encontrada",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PutMapping("/disable/{accountId}")
    public ResponseEntity<AccountDto> disableAccount(@Parameter(description = "ID de la cuenta a deshabilitar", example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable UUID accountId) {
        return accountService.disableAccount(accountId);
    }
}
