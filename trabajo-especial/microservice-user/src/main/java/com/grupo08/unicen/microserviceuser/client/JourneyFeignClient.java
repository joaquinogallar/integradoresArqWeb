package com.grupo08.unicen.microserviceuser.client;

import com.grupo08.unicen.microservicejourney.dto.JourneyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

@FeignClient("MICROSERVICE-JOURNEY")
public interface JourneyFeignClient {
    @GetMapping("/")
    public ResponseEntity<List<JourneyDto>> getAll();

    @PostMapping("/crear/{monopatinId}/usuario/{usuarioId}")
    public void createViaje(@PathVariable("monopatinId") UUID monopatinId, @PathVariable("usuarioId") UUID usuarioId);

    @PutMapping("/endViaje/{idViaje}")
    public ResponseEntity<JourneyDto> endViaje(@PathVariable UUID idViaje);

    @PostMapping("/crear/{monopatinId}/usuario/{usuarioId}")
    public void createViaje(@PathVariable("monopatinId") Long monopatinId, @PathVariable("usuarioId") Long usuarioId);
}
