package com.grupo08.unicen.microserviceuser.client;

import com.grupo08.unicen.microserviceuser.model.JourneyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "MICROSERVICE-JOURNEY", url = "http://localhost:8051")
public interface JourneyFeignClient {
    @GetMapping("/api/journeys/")
    ResponseEntity<List<JourneyDto>> getAll();

    @PostMapping("/api/journeys/crear/{monopatinId}/usuario/{usuarioId}")
    ResponseEntity<JourneyDto> createViaje(@PathVariable("monopatinId") UUID monopatinId, @PathVariable("usuarioId") UUID usuarioId);

    @PutMapping("/api/journeys/endViaje/{idViaje}")
    ResponseEntity<JourneyDto> endViaje(@PathVariable UUID idViaje);
}
