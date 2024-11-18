package com.grupo08.unicen.microservicejourney.controller;

import com.grupo08.unicen.microservicejourney.dto.JourneyDto;
import com.grupo08.unicen.microservicejourney.model.MonopatinDto;
import com.grupo08.unicen.microservicejourney.service.JourneyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/journeys")
public class JourneyController {

    JourneyService viajeService ;

    public JourneyController(JourneyService journeyService) {
        this.viajeService = journeyService;
    }

    @Operation(summary = "Obtener todos los viajes", description = "Devuelve una lista de todos los viajes registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de viajes obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = JourneyDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<JourneyDto>> getAllJourneys(){
        return ResponseEntity.ok(viajeService.getAllJoruneys());
    }

    @Operation(summary = "Obtener monopatines por cantidad de viajes y año", description = "Devuelve una lista de monopatines con base en la cantidad de viajes realizados en un año específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de monopatines obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class))),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/monopatines/viajes/{cant}/{anio}")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesByViaje(
            @Parameter(description = "Cantidad de viajes realizados por los monopatines", example = "5")
            @PathVariable int cant,
            @Parameter(description = "Año de los viajes", example = "2023")
            @PathVariable int anio) {
        try {
            return ResponseEntity.ok(viajeService.getMonopatinesByXViajes(cant, anio));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Finalizar un viaje", description = "Finaliza un viaje específico por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje finalizado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JourneyDto.class))),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/endViaje/{idViaje}")
    public ResponseEntity<JourneyDto> endViaje(
            @Parameter(description = "ID del viaje a finalizar", example = "f1e2a74f-b34b-4555-90ac-85f0678ff45e")
            @PathVariable UUID idViaje) {
        try {
            return ResponseEntity.ok(viajeService.endViaje(idViaje));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Crear un nuevo viaje", description = "Crea un nuevo viaje asociando un monopatín, un usuario y una cuenta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JourneyDto.class))),
            @ApiResponse(responseCode = "500", description = "Error al crear el viaje", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/create/{monopatinId}/users/{usuarioId}/accounts/{accountId}")
    public ResponseEntity<JourneyDto> createViaje(
            @Parameter(description = "ID del monopatín", example = "e4a987b1-c57e-4e02-810d-d7ecac56fcda")
            @PathVariable("monopatinId") UUID monopatinId,
            @Parameter(description = "ID del usuario", example = "1fcd8ee2-1b97-4607-a568-66e9b2a9e17b")
            @PathVariable("usuarioId") UUID usuarioId,
            @Parameter(description = "ID de la cuenta asociada", example = "7f6232de-b99d-47f1-a484-3275f7b9203f")
            @PathVariable("accountId") UUID accountId) {
        try {
            return ResponseEntity.ok(viajeService.createViaje(monopatinId, usuarioId, accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Obtener viajes por monopatín", description = "Devuelve una lista de viajes asociados a un monopatín específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de viajes obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = JourneyDto.class)))
    })
    @GetMapping("/monopatines/{monopatinId}")
    public ResponseEntity<List<JourneyDto>> getViajeByMonopatin(
            @Parameter(description = "ID del monopatín", example = "a12d3d2e-f1a0-404b-b9a4-2b115f58e370")
            @PathVariable UUID monopatinId) {
        return viajeService.getViajeByMonopatin(monopatinId);
    }

    @Operation(summary = "Obtener facturación entre meses", description = "Devuelve la facturación de los viajes entre dos meses específicos del mismo año.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Facturación obtenida correctamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/facturado/{year}/{mes}/{mes2}")
    public int getFacturadoEntreMeses(
            @Parameter(description = "Año de la facturación", example = "2023")
            @PathVariable int year,
            @Parameter(description = "Mes de inicio de la facturación", example = "1")
            @PathVariable int mesInicio,
            @Parameter(description = "Mes de finalización de la facturación", example = "6")
            @PathVariable int mesFinal) {
        return viajeService.getFacturadoEntreMeses(year, mesInicio, mesFinal);
    }
}